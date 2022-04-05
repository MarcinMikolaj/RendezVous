package project.rendezvous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.rendezvous.panel.FileManager;
import project.rendezvous.panel.Picture;
import project.rendezvous.panel.localization.GeoLocalization;
import project.rendezvous.panel.localization.GeoLocalizationRepository;
import project.rendezvous.panel.localization.GeoLocalizationService;
import project.rendezvous.panel.preferences.UserPreferences;
import project.rendezvous.panel.preferences.UserPreferencesRepository;
import project.rendezvous.registration.*;

import java.io.File;

@Controller
public class HelloController {

    private UserRepository userRepository;
    private UserDescriptionRepository userDescriptionRepository;
    private UserFriendsRepository userFriendsRepository;
    private UserPreferencesRepository userPreferencesRepository;
    private GeoLocalizationService geoLocalizationService;
    private GeoLocalizationRepository geoLocalizationRepository;
    private FileManager fileManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setCustomerRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserDescriptionRepository(UserDescriptionRepository userDescriptionRepository){
        this.userDescriptionRepository = userDescriptionRepository;
    }

    @Autowired
    public void setUserFriendsRepository(UserFriendsRepository userFriendsRepository){
        this.userFriendsRepository = userFriendsRepository;
    }

    @Autowired
    public void setGeoLocalizationService(GeoLocalizationService geoLocalizationService){
        this.geoLocalizationService = geoLocalizationService;
    }

    @Autowired
    public void setUserPreferencesRepository(UserPreferencesRepository userPreferencesRepository){
        this.userPreferencesRepository = userPreferencesRepository;
    }

    @Autowired
    public void setGeoLocalizationRepository(GeoLocalizationRepository geoLocalizationRepository){
        this.geoLocalizationRepository = geoLocalizationRepository;
    }

    @Autowired
    public void setFileManager(FileManager fileManager){
        this.fileManager = fileManager;
    }


    @GetMapping(value="/test")
    public String test(){

        return "test";
    }

    @RequestMapping(path = "/data", method = RequestMethod.GET)
    @ResponseBody
    public User response(){
        User user = new User("marcin3246a51@o2.pl", "sS3@ddd1", "ciasna karolina", "woman", 18, true, "USER");
        return user;
    }


    @GetMapping(value="/m")
    public String m(){
        System.out.println("MongoDB test");

        userRepository.deleteAll();
        userDescriptionRepository.deleteAll();
        userFriendsRepository.deleteAll();
        userPreferencesRepository.deleteAll();
        geoLocalizationRepository.deleteAll();

//        GeoLocalization{id='null', email='bakłażanek23@gmail.com', latitude=49.581242, longitude=20.668631}
//   UserPreferences     String email, String gender, int minAge, int maxAge, int distance, String relationshipStatus, String sexualOrientation
//        UserFilters String email, String name, String gender, int age, String sexualOrientation, String relationshipStatus, String aboutMeDescription

        User user1 = new User("marcin3246a51@o2.pl", "sS3@ddd1", "marcin3246", "woman", 18, true, "USER");
        UserFriends userFriends1 = new UserFriends("marcin3246a51@o2.pl");
        userFriends1.getLikeUsers().add("bakłażanek23@gmail.com");
        userFriends1.getLikeAndRejectUsers().add("bakłażanek23@gmail.com");
        userFriends1.getLikeUsers().add("karolina455@gmail.com");
        userFriends1.getLikeAndRejectUsers().add("karolina455@gmail.com");
        userFriends1.getFormedPairsWithUsersList().add("karolina455@gmail.com");
        UserDescription userDescription1 = new UserDescription("marcin3246a51@o2.pl", "Marcin", "man", 18, "hetero", "single" ,"tu jest mój opis");
        UserPreferences userPreferences1 = new UserPreferences("marcin3246a51@o2.pl", "all", 18, 80, 160, "all", "all");
        // 50.05805760849107, 19.943355348832057 Kraków
        GeoLocalization geoLocalization1 = new GeoLocalization("marcin3246a51@o2.pl", 50.05805760849107, 19.943355348832057);
        userDescription1.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\marcin3246a51@o2.pl\\marcin3246a51@o2.pl-img-0");
        userDescription1.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\marcin3246a51@o2.pl\\marcin3246a51@o2.pl-img-1");
        userDescription1.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\marcin3246a51@o2.pl\\marcin3246a51@o2.pl-img-2");
        userDescription1.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\marcin3246a51@o2.pl\\marcin3246a51@o2.pl-img-3");



        User user2 = new User("bakłażanek23@gmail.com", "sS3@ddd2", "bakłażanek23", "man", 23, true, "USER");
        UserFriends userFriends2 = new UserFriends("bakłażanek23@gmail.com");

        userFriends2.getLikeUsers().add("karolina455@gmail.com");
        userFriends2.getLikeAndRejectUsers().add("karolina455@gmail.com");
        userFriends2.getFormedPairsWithUsersList().add("karolina455@gmail.com");

        UserDescription userDescription2 = new UserDescription("bakłażanek23@gmail.com", "bakłażanek23", "man", 18, "hetero", "single" ,"tu jest mój opis");
        UserPreferences userPreferences2 = new UserPreferences("bakłażanek23@gmail.com", "all", 18, 80, 160, "all", "all");
        //  49.703341514153756, 20.420093746602387 Biegonice
        GeoLocalization geoLocalization2 = new GeoLocalization("bakłażanek23@gmail.com", 49.581242, 20.668631);
        userDescription2.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\bakłażanek23@gmail.com\\bakłażanek23@gmail.com-img-0");
        userDescription2.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\bakłażanek23@gmail.com\\bakłażanek23@gmail.com-img-1");
        userDescription2.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\bakłażanek23@gmail.com\\bakłażanek23@gmail.com-img-2");
        userDescription2.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\bakłażanek23@gmail.com\\bakłażanek23@gmail.com-img-3");



        User user3 = new User("karolina455@gmail.com", "sS3@ddd3", "karolina", "woman", 41, true, "USER");
        UserFriends userFriends3 = new UserFriends("karolina455@gmail.com");
        userFriends3.getLikeUsers().add("marcin3246a51@o2.pl");
        userFriends3.getLikeAndRejectUsers().add("marcin3246a51@o2.pl");
        userFriends3.getFormedPairsWithUsersList().add("marcin3246a51@o2.pl");

        userFriends3.getLikeUsers().add("bakłażanek23@gmail.com");
        userFriends3.getLikeAndRejectUsers().add("bakłażanek23@gmail.com");
        userFriends3.getFormedPairsWithUsersList().add("bakłażanek23@gmail.com");

        UserDescription userDescription3 = new UserDescription("karolina455@gmail.com", "Karolina", "man", 18, "hetero", "single" ,"tu jest mój opis");
        UserPreferences userPreferences3 = new UserPreferences("karolina455@gmail.com", "all", 18, 80, 160, "all", "all");
        // 49.583921583287804, 20.664625247532044 Koło biegonic
        GeoLocalization geoLocalization3 = new GeoLocalization("karolina455@gmail.com",49.583921583287804, 20.664625247532044);
        userDescription3.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\karolina455@gmail.com\\karolina455@gmail.com-img-0");
        userDescription3.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\karolina455@gmail.com\\karolina455@gmail.com-img-1");
        userDescription3.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\karolina455@gmail.com\\karolina455@gmail.com-img-2");
        userDescription3.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\karolina455@gmail.com\\karolina455@gmail.com-img-3");


        User user4 = new User("agent67@gmail.com", "sS3@ddd4", "agent67", "man", 58, true, "USER");
        UserFriends userFriends4 = new UserFriends("agent67@gmail.com");
        UserDescription userDescription4 = new UserDescription("agent67@gmail.com", "agent67", "man", 18, "hetero", "single" ,"tu jest mój opis");
        UserPreferences userPreferences4 = new UserPreferences("agent67@gmail.com", "all", 18, 80, 160, "all", "all");
        // 49.67164448452305, 20.684180883149725   Wielogłowy
        GeoLocalization geoLocalization4 = new GeoLocalization("agent67@gmail.com", 49.67164448452305, 20.684180883149725);
        userDescription4.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\agent67@gmail.com\\agent67@gmail.com-img-0");
        userDescription4.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\agent67@gmail.com\\agent67@gmail.com-img-1");
        userDescription4.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\agent67@gmail.com\\agent67@gmail.com-img-2");
        userDescription4.getPathToImgList().add("C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\agent67@gmail.com\\agent67@gmail.com-img-3");



        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        userRepository.save(user1);
        userFriendsRepository.save(userFriends1);
        userDescriptionRepository.save(userDescription1);
        userPreferencesRepository.save(userPreferences1);
        geoLocalizationRepository.save(geoLocalization1);

        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
        userRepository.save(user2);
        userFriendsRepository.save(userFriends2);
        userDescriptionRepository.save(userDescription2);
        userPreferencesRepository.save(userPreferences2);
        geoLocalizationRepository.save(geoLocalization2);

        user3.setPassword(passwordEncoder.encode(user3.getPassword()));
        userRepository.save(user3);
        userFriendsRepository.save(userFriends3);
        userDescriptionRepository.save(userDescription3);
        userPreferencesRepository.save(userPreferences3);
        geoLocalizationRepository.save(geoLocalization3);

        user4.setPassword(passwordEncoder.encode(user4.getPassword()));
        userRepository.save(user4);
        userFriendsRepository.save(userFriends4);
        userDescriptionRepository.save(userDescription4);
        userPreferencesRepository.save(userPreferences4);
        geoLocalizationRepository.save(geoLocalization4);

//        boolean result1 = userRepository.existsByEmail("marcin3246a52@o2.pl");
//        System.out.println("1 " + result1);
//
//        boolean result2 = userRepository.existsByEmail("marcin3246a92@o2.pl");
//        System.out.println("2 " + result2);
//
//        boolean result3 = userRepository.existsByNick("ciasna karolina");
//        System.out.println("3 " + result3);
//
//        boolean result4 = userRepository.existsByNick("ciana karolina");
//        System.out.println("4 " + result4);
//
//        User user = userRepository.findByEmail("marcin3246a51@o2.pl");
//        System.out.println(user.toString());

//        List<User> getUsers = userRepository.findByAgeBetween(18,23);
//        System.out.println(getUsers.size());
//        getUsers.forEach(user -> System.out.println(user.toString()));

        return "test";
    }

    //    @GetMapping(value="/test1")
//    public String signInGET(){
//        GeoLocalization secondLocalization = new GeoLocalization( 39.7645187, -104.9951948);
//        GeoLocalization firstLocalization = new GeoLocalization( 40.6976637, -74.1197643);
//        geoLocalizationService.getDistanceBetweenUsers(firstLocalization, secondLocalization);
//        return "test";
//    }

}
