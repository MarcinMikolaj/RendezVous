package project.rendezvous.communicator.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.rendezvous.communicator.ChatMessage;

@Service
public class ConversationService {

    private UserConversationRepository userConversationRepository;

    @Autowired
    public void setUserConversationRepository(UserConversationRepository userConversationRepository) {
        this.userConversationRepository = userConversationRepository;
    }

    // Allows you to save messages in the database
    public void saveConversation(String ownerEmail, String recipientEmail, ChatMessage chatMessage){

        UserConversations userConversations = userConversationRepository.findByEmail(ownerEmail);
        getConversationListByEmail(recipientEmail, userConversations).getChatMessageList().add(chatMessage);
        userConversationRepository.save(userConversations);

    }

    public Conversation getConversation(String ownerEmail, String recipientEmail){
        UserConversations userConversations = userConversationRepository.findByEmail(ownerEmail);
        return getConversationListByEmail(recipientEmail, userConversations);
    }

    // Returns conversations with the user if it exists
    private Conversation getConversationListByEmail(String recipientEmail, UserConversations userConversations){

        if(userConversations.getConversationList().isEmpty() || recipientEmail.isEmpty()){
            return null;
        }

        for(Conversation conversation: userConversations.getConversationList()){
            if(conversation.getRecipientEmail().equals(recipientEmail)){
                return conversation;
            }
        }

        return null;
    }
}
