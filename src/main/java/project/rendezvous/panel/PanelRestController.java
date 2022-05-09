package project.rendezvous.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.rendezvous.other.ConsoleColors;
import project.rendezvous.panel.localization.GeoLocalization;
import project.rendezvous.panel.localization.GeoLocalizationRepository;
import project.rendezvous.panel.localization.GeoLocalizationService;
import project.rendezvous.panel.preferences.UserPreferences;
import project.rendezvous.registration.*;
import project.rendezvous.registration.userDescription.UserDescription;
import project.rendezvous.registration.userDescription.UserDescriptionRepository;
import project.rendezvous.registration.UserRepository;

import java.security.Principal;

@RestController
public class PanelRestController {

    private UserRepository userRepository;
    private PanelService panelService;
    private UserDescriptionRepository userDescriptionRepository;
    private GeoLocalizationRepository geoLocalizationRepository;
    private GeoLocalizationService geoLocalizationService;
    private FileManager fileManager;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserDescriptionRepository(UserDescriptionRepository userDescriptionRepository){
        this.userDescriptionRepository = userDescriptionRepository;
    }

    @Autowired
    public void setPanelService(PanelService panelService){
        this.panelService = panelService;
    }

    @Autowired
    public void setGeoLocalizationRepository(GeoLocalizationRepository geoLocalizationRepository){
        this.geoLocalizationRepository = geoLocalizationRepository;
    }

    @Autowired
    public void setGeoLocalizationService(GeoLocalizationService geoLocalizationService) {
        this.geoLocalizationService = geoLocalizationService;
    }

    @Autowired
    public void setFileManager(FileManager fileManager){
        this.fileManager = fileManager;
    }

    // Allows you to send actual logged user credentials to the client
    @RequestMapping(value = "/panel/api/credentials/send", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User sendAuthentication(Principal principal){

        if(principal.getName() != null){
            User user = userRepository.findByEmail(principal.getName());
            System.out.println(user.toString());
            return user;
        } else{
            System.out.println("ERROR - PanelRestController - sendAuthentication");
            return null;
        }
    }

    // Allows you to send 'userDescription' to actual logged user
    @RequestMapping(path="panel/api/userDescription/send", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDescription sendUserDescription(Principal principal){

        UserDescription userDescription = userDescriptionRepository.findByEmail(principal.getName());

        if(!userDescription.getPathToImgList().isEmpty()){
            for(String path: userDescription.getPathToImgList()){
                Picture picture = fileManager.deserializationObjectAndGetFromDirectory(path);
                userDescription.getPictures().add(picture);
            }
        }else{
            System.out.println(ConsoleColors.YELLOW + "List is empty" + ConsoleColors.RESET);
        }

        return userDescription;
    }

    // Allows you to get 'userDescription' to update form client
    @RequestMapping(path = "/panel/api/userDescription/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserDescription(@RequestBody UserDescription userDescription, Principal principal){

        UserDescription actualUserDescription = userDescriptionRepository.findByEmail(principal.getName());
        String actualId = actualUserDescription.getId();

        UserDescription toUpdate = userDescription;
        toUpdate.setId(actualId);
        toUpdate.setEmail(principal.getName());
        toUpdate.setPathToImgList(actualUserDescription.getPathToImgList());

        userDescriptionRepository.save(toUpdate);

    }


    // It allows you to send next candidate to client
    @RequestMapping(path = "/panel/api/userDescription/sendNext", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDescription sendNextCandidateToClient(Principal principal){

        UserDescription userDescription = panelService.getNextUserDescription(principal.getName());

        GeoLocalization firstLocalization = geoLocalizationRepository.findByEmail(principal.getName());
        GeoLocalization secondLocalization = geoLocalizationRepository.findByEmail(userDescription.getEmail());

        int kilometersAway = geoLocalizationService.getDistanceBetweenUsers(firstLocalization, secondLocalization);

        userDescription.setKilometersAway(kilometersAway);

        if(!userDescription.getPathToImgList().isEmpty()){
            for(String path: userDescription.getPathToImgList()){
                Picture picture = fileManager.deserializationObjectAndGetFromDirectory(path);
                userDescription.getPictures().add(picture);
            }
        }else{
            System.out.println(ConsoleColors.YELLOW + "List is empty" + ConsoleColors.RESET);
        }

        return userDescription;
    }


    //  It allows you to get from client and update userFriends object
    @RequestMapping(path = "/panel/api/userFriends/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLikeAndRejectUsers(@RequestBody ToUpdateLikeOrRejectUser toUpdateLikeOrRejectUser){
//        System.out.println("Aktualizacja listy użytkowników polubionych: " + toUpdateLikeOrRejectUser.toString());
        panelService.updateListLikeOrRejectUsers(toUpdateLikeOrRejectUser);
    }


    // Receive UserPreference object form client
    @RequestMapping(path = "/panel/api/userPreferences/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getFromClientUserPreferences(@RequestBody UserPreferences userPreferences){
        System.out.println(userPreferences.toString());
        panelService.updateUserPreferences(userPreferences.getEmail(), userPreferences);
    }

    // Send to client his UserPreferences object from database
    @RequestMapping(path = "/panel/api/userPreferences/send", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserPreferences sendToClientUserPreferences(Principal principal){
        return panelService.getUserPreferencesByEmail(principal.getName());
    }

    // Receive GeoLocalization object form client
    @RequestMapping(path = "/panel/api/geoLocalization/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getGeoLocalizationFromClient(@RequestBody GeoLocalization geoLocalization){
        System.out.println(geoLocalization);

        // Update last GeoLocalization in database
        String id = geoLocalizationRepository.findByEmail(geoLocalization.getEmail()).getId();
        geoLocalization.setId(id);
        geoLocalizationRepository.save(geoLocalization);

    }


//    ********************* Picture *********************************

    @RequestMapping(path = "/panel/api/picture/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getPictureFromClient(@RequestBody Picture picture, Principal principal){

            String emailCurrentLoggedUser = principal.getName();

            fileManager.createFolder(emailCurrentLoggedUser, picture);
            String pathToImg = fileManager.serializeObjectAndSaveInDirectory(emailCurrentLoggedUser, picture);

            UserDescription userDescriptionToUpdate = userDescriptionRepository.findByEmail(emailCurrentLoggedUser);
            userDescriptionToUpdate.getPathToImgList().add(pathToImg);
            userDescriptionRepository.save(userDescriptionToUpdate);


        System.out.println(picture);
    }

    @RequestMapping(path = "/panel/api/picture/send", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Picture sendPictureToClient(Principal principal){

//        Picture picture = fileManager.deserialization("principal");
//        System.out.println(picture.toString());

        return null;
    }


}
