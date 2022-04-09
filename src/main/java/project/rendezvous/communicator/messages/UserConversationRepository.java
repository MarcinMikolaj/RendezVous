package project.rendezvous.communicator.messages;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserConversationRepository extends MongoRepository<UserConversations, String> {

    public UserConversations findByEmail(String email);

}
