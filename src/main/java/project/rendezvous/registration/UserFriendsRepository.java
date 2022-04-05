package project.rendezvous.registration;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendsRepository extends MongoRepository<UserFriends, String> {

    UserFriends findByEmail(String email);

}
