package project.rendezvous.panel.preferences;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPreferencesRepository extends MongoRepository<UserPreferences, String> {

    UserPreferences findByEmail(String email);

}
