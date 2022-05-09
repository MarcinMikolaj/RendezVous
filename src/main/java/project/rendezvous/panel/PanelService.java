package project.rendezvous.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.rendezvous.panel.localization.GeoLocalization;
import project.rendezvous.panel.localization.GeoLocalizationRepository;
import project.rendezvous.panel.localization.GeoLocalizationService;
import project.rendezvous.panel.preferences.UserPreferences;
import project.rendezvous.panel.preferences.UserPreferencesRepository;
import project.rendezvous.registration.*;
import project.rendezvous.registration.userDescription.UserDescription;
import project.rendezvous.registration.userDescription.UserDescriptionRepository;
import project.rendezvous.registration.userFriends.UserFriends;
import project.rendezvous.registration.userFriends.UserFriendsRepository;
import project.rendezvous.registration.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PanelService {

    private UserRepository userRepository;
    private UserDescriptionRepository userDescriptionRepository;
    private UserFriendsRepository userFriendsRepository;
    private UserPreferencesRepository userPreferencesRepository;

    private GeoLocalizationRepository geoLocalizationRepository;
    private GeoLocalizationService geoLocalizationService;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserDetailsRepository(UserFriendsRepository userFriendsRepository){
        this.userFriendsRepository = userFriendsRepository;
    }

    @Autowired
    public void setUserDescriptionRepository(UserDescriptionRepository userDescriptionRepository){
        this.userDescriptionRepository = userDescriptionRepository;
    }

    @Autowired
    public void setUserPreferencesRepository(UserPreferencesRepository userPreferencesRepository){
        this.userPreferencesRepository = userPreferencesRepository;
    }

    @Autowired
    public void setGeoLocalizationRepository(GeoLocalizationRepository geoLocalizationRepository, GeoLocalizationService geoLocalizationService){
        this.geoLocalizationRepository = geoLocalizationRepository;
        this.geoLocalizationService = geoLocalizationService;
    }

    // Allows you to update the list of liked and rejected users
    // Returns the object being updated
    public UserFriends updateListLikeOrRejectUsers(ToUpdateLikeOrRejectUser toUpdateLikeOrRejectUser){

        boolean key = true;

        UserFriends userFriendsListToUpdate = userFriendsRepository.findByEmail(toUpdateLikeOrRejectUser.getSelectingUserEmail());

        if(checkUserAssignedBefore(userFriendsListToUpdate, toUpdateLikeOrRejectUser.getSelectedUserEmail())){
            System.out.println("ERROR PanelService - checkUserAssignedBefore");
            return null;
        }

        userFriendsListToUpdate.getLikeAndRejectUsers().add(toUpdateLikeOrRejectUser.getSelectedUserEmail());

        if(toUpdateLikeOrRejectUser.getChoice().equals("like")){

            UserFriends userFriendsToUpdateCandidate = userFriendsRepository.findByEmail(toUpdateLikeOrRejectUser.getSelectedUserEmail());
            userFriendsListToUpdate.getLikeUsers().add(toUpdateLikeOrRejectUser.getSelectedUserEmail());
            key = createPair(userFriendsListToUpdate, userFriendsToUpdateCandidate);
            System.out.println(key);
        }
        if(toUpdateLikeOrRejectUser.getChoice().equals("reject")){
            userFriendsListToUpdate.getRejectedUsers().add(toUpdateLikeOrRejectUser.getSelectedUserEmail());
            key = false;
        }

            userFriendsRepository.save(userFriendsListToUpdate);

       return userFriendsListToUpdate;
    }


    // It allows you to create pairs by updating identifiers (email) for lists of people who like each project.rendezvous.other
    // Accepts UserFriends objects for the select and the select
    private boolean createPair(UserFriends userFriendsChooser, UserFriends userFriendsCandidate){

        boolean chooserKey = false;
        boolean candidateKey = false;

        chooserKey = userFriendsChooser.getLikeUsers().stream()
                .anyMatch(chooserEmail -> chooserEmail.equals(userFriendsCandidate.getEmail()));

        candidateKey = userFriendsCandidate.getLikeUsers().stream()
                .anyMatch(candidateEmail -> candidateEmail.equals(userFriendsChooser.getEmail()));

       if(chooserKey && candidateKey){
           userFriendsChooser.getFormedPairsWithUsersList().add(userFriendsCandidate.getEmail());
           userFriendsCandidate.getFormedPairsWithUsersList().add(userFriendsChooser.getEmail());
           userFriendsRepository.save(userFriendsChooser);
           userFriendsRepository.save(userFriendsCandidate);
           return true;
       }

       return false;
    }


    // Checks if the user has not been previously subscribed to the list of liked or rejected users
    private boolean checkUserAssignedBefore(UserFriends userFriendsListToUpdate, String emailCandidate){

        if(userFriendsListToUpdate.getLikeAndRejectUsers().contains(emailCandidate)){
            return true;
        }else{
            return false;
        }
    }



    // Returns the selected user
    public UserDescription getNextUserDescription(String chooserEmail){

        List<UserDescription> userDescriptionsFromMongoDB = getNotSelectedUserDescription(chooserEmail);
        List<UserDescription> selectedUsersDescription = sendUsersThroughFilters(chooserEmail, userDescriptionsFromMongoDB);
        UserDescription result = userRandomization(selectedUsersDescription);

        return result;
    }

    private List<UserDescription> getNotSelectedUserDescription (String chooserEmail){

        boolean key = false;
        List<UserDescription> resultList = new ArrayList<UserDescription>();

        List<UserDescription> userDescriptionFromMongoDB = userDescriptionRepository.findAll();
        UserFriends chooserUserFriends = userFriendsRepository.findByEmail(chooserEmail);

        List<String> likeAndRejectUsers = chooserUserFriends.getLikeAndRejectUsers();

        for(UserDescription candidate: userDescriptionFromMongoDB){
           for(String likedOrRejected: likeAndRejectUsers){
             if(candidate.getEmail().equals(likedOrRejected) == true){
                  key = true;
                  break;
              }
           }

           if(key == false){
               resultList.add(candidate);
           }
            key = false;
        }


        // Responsible for removing the caller from the list of the chosen ones
        resultList = resultList.stream()
                .filter(userDescription ->  userDescription.getEmail().equals(chooserEmail) == false )
                .collect(Collectors.toList());

        return resultList;
    }


    // Is responsible for selecting users in accordance with the filter
    private List<UserDescription> sendUsersThroughFilters(String chooserEmail, List<UserDescription> userDescriptions){

        UserPreferences chooserPreferences = userPreferencesRepository.findByEmail(chooserEmail);
        GeoLocalization chooserLocalization = geoLocalizationRepository.findByEmail(chooserEmail);

        List<UserDescription> resultUserDescriptionList;

        resultUserDescriptionList = userDescriptions.stream()
                .filter(userDescription -> {

                    if(!chooserPreferences.getGender().equals("all")){
                        return userDescription.getGender().equals(chooserPreferences.getGender());
                    }else{return true;}

                })
                .filter(userDescription -> userDescription.getAge() <= chooserPreferences.getMaxAge())
                .filter(userDescription -> userDescription.getAge() >= chooserPreferences.getMinAge())
                .filter(userDescription -> {

                    if(!chooserPreferences.getRelationshipStatus().equals("all")){
                        return  userDescription.getRelationshipStatus().equals(chooserPreferences.getRelationshipStatus());
                    }else { return true; }

                })
                .filter(userDescription -> {

                    if(!chooserPreferences.getSexualOrientation().equals("all")){
                        return userDescription.getSexualOrientation().equals(chooserPreferences.getSexualOrientation());
                    } else { return true; }

                })
                .filter(userDescription ->{

                    System.out.println(geoLocalizationService.getDistanceBetweenUsers(geoLocalizationRepository.findByEmail( userDescription.getEmail()), chooserLocalization));

                    return geoLocalizationService.getDistanceBetweenUsers(geoLocalizationRepository.findByEmail( userDescription.getEmail()), chooserLocalization)
                            <= chooserPreferences.getDistance();
                        })

                .collect(Collectors.toList());

        return resultUserDescriptionList;

    }

    private UserDescription userRandomization(List<UserDescription> userDescription){

        if(userDescription.isEmpty()){
            System.out.println("userRandomization - null");
            return null;

        }else {
            UserDescription result = userDescription.get(0);
//            System.out.println(result.toString());
            return result;
        }
    }


    public void updateUserPreferences(String email, UserPreferences userPreferences){

        String userPreferencesToUpdateID = userPreferencesRepository.findByEmail(email).getId();
        userPreferences.setId(userPreferencesToUpdateID);

        userPreferencesRepository.save(userPreferences);
    }

    public UserPreferences getUserPreferencesByEmail(String email){
        UserPreferences userPreferences = userPreferencesRepository.findByEmail(email);
        return userPreferences;
    }

}
