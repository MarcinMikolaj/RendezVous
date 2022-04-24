package project.rendezvous.registration.userDescription;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.rendezvous.registration.userDescription.UserDescription;

public interface UserDescriptionRepository extends MongoRepository<UserDescription, String> {

    UserDescription findByEmail(String email);

}
