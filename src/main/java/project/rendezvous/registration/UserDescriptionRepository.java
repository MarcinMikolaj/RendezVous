package project.rendezvous.registration;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDescriptionRepository extends MongoRepository<UserDescription, String> {

    UserDescription findByEmail(String email);

}
