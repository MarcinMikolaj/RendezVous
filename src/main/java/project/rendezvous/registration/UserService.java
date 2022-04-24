package project.rendezvous.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.rendezvous.communicator.messages.UserConversationRepository;
import project.rendezvous.communicator.messages.UserConversations;
import project.rendezvous.panel.localization.GeoLocalization;
import project.rendezvous.panel.localization.GeoLocalizationRepository;
import project.rendezvous.panel.preferences.UserPreferences;
import project.rendezvous.panel.preferences.UserPreferencesRepository;
import project.rendezvous.registration.userDescription.UserDescription;
import project.rendezvous.registration.userDescription.UserDescriptionRepository;
import project.rendezvous.registration.userFriends.UserFriends;
import project.rendezvous.registration.userFriends.UserFriendsRepository;

import java.util.Set;
import java.util.HashMap;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Service
public class UserService {

    private UserRepository userRepository;
    private Validator validator;
    private UserFriendsRepository userFriendsRepository;
    private UserDescriptionRepository userDescriptionRepository;
    private UserPreferencesRepository userPreferencesRepository;
    private UserConversationRepository userConversationRepository;
    private GeoLocalizationRepository geoLocalizationRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserFriendsRepository userFriendsRepository, UserDescriptionRepository userDescriptionRepository,
                       UserPreferencesRepository userPreferencesRepository, UserConversationRepository userConversationRepository,
                       GeoLocalizationRepository geoLocalizationRepository) {
        this.userRepository = userRepository;
        this.userFriendsRepository = userFriendsRepository;
        this.userDescriptionRepository = userDescriptionRepository;
        this.userPreferencesRepository = userPreferencesRepository;
        this.userConversationRepository = userConversationRepository;
        this.geoLocalizationRepository = geoLocalizationRepository;
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void createAccount(User user) {

        Set<ConstraintViolation<User>> validationErrors = validator.validate(user);

        if(!validationErrors.isEmpty()){
            validationErrors.forEach(err -> System.err.println(err.getMessage()));
        }else {

            String email = user.getEmail();

            user.setRole("USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            UserFriends userFriends = new UserFriends(email);
            userFriendsRepository.save(userFriends);

            UserDescription userDescription = new UserDescription(email, "brak", user.getGender(), user.getAge(), "brak", "brak", "brak");
            userDescriptionRepository.save(userDescription);

            UserPreferences userPreferences = new UserPreferences(email, "all", 18, 80, 160, "all", "all");
            userPreferencesRepository.save(userPreferences);

            UserConversations userConversations = new UserConversations(email);
            userConversationRepository.save(userConversations);

            GeoLocalization geoLocalization = new GeoLocalization(email, 0,0);
            geoLocalizationRepository.save(geoLocalization);

        }
    }
}

