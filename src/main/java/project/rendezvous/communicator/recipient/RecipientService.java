package project.rendezvous.communicator.recipient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.rendezvous.communicator.ChatMessage;
import project.rendezvous.communicator.messages.Conversation;
import project.rendezvous.communicator.messages.UserConversationRepository;
import project.rendezvous.communicator.messages.UserConversations;
import project.rendezvous.panel.FileManager;
import project.rendezvous.panel.Picture;
import project.rendezvous.registration.*;
import project.rendezvous.registration.userDescription.UserDescription;
import project.rendezvous.registration.userDescription.UserDescriptionRepository;
import project.rendezvous.registration.userFriends.UserFriends;
import project.rendezvous.registration.userFriends.UserFriendsRepository;
import project.rendezvous.registration.UserRepository;

import java.util.LinkedList;
import java.util.stream.Stream;

@Service
public class RecipientService {

    private UserFriendsRepository userFriendsRepository;
    private UserDescriptionRepository userDescriptionRepository;
    private UserConversationRepository userConversationRepository;
    private UserRepository userRepository;
    private FileManager fileManager;

    @Autowired
    public void setUserFriendsRepository(UserFriendsRepository userFriendsRepository){
        this.userFriendsRepository = userFriendsRepository;
    }

    @Autowired
    public void setUserDescriptionRepository(UserDescriptionRepository userDescriptionRepository){
        this.userDescriptionRepository = userDescriptionRepository;
    }

    @Autowired
    public void setUserConversationRepository(UserConversationRepository userConversationRepository) {
        this.userConversationRepository = userConversationRepository;
    }

    @Autowired
    public void setFileManager(FileManager fileManager){
        this.fileManager = fileManager;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Returns a list of recipients that can be contacted by the user whose identifier is taken as an argument
    public RecipientList getRecipientList(String email){

        RecipientList recipientList = new RecipientList();

        UserFriends userFriends = userFriendsRepository.findByEmail(email);

        recipientList.setUserEmail(email);

        userFriends.getFormedPairsWithUsersList().stream()
                .filter(recipientEmail -> recipientEmail != null)
                .peek(recipientEmail -> recipientList.getRecipientList().add(createRecipient(email, recipientEmail)))
                .count();

        return  recipientList;
    }

    private Recipient createRecipient(String loggedUserEmail, String recipientEmail){

        UserDescription userDescription = userDescriptionRepository.findByEmail(recipientEmail);

        User user = userRepository.findByEmail(recipientEmail); // ?
        String path = userDescription.getPathToImgList().get(0);
        Picture picture = fileManager.deserializationObjectAndGetFromDirectory(path);

        Recipient recipient = new Recipient();
        recipient.setEmail(userDescription.getEmail());
        recipient.setNick(user.getNick());
        recipient.setName(userDescription.getName());
        recipient.setAge(userDescription.getAge());
        recipient.setCity(userDescription.getCity());
        recipient.setLastMessage(getLastMessage(loggedUserEmail, recipientEmail));
        recipient.setUniversity(userDescription.getUniversity());
        recipient.setWork(userDescription.getWork());
        recipient.setProfileImg(picture);
        recipient.setActive(false);

        return recipient;
    }


    // It allows you (actual logged user) to get last chatMessage object sent to you from 'recipient' with you exchange messages
    private ChatMessage getLastMessage(String loggedUserEmail, String recipientEmail){

        UserConversations userConversations = userConversationRepository.findByEmail(recipientEmail);

        Conversation conversation = userConversations.getConversationList().stream()
                .filter(conv -> conv.getRecipientEmail().equals(loggedUserEmail))
                .findFirst().orElse(null);

        ChatMessage chatmessage = reverse(conversation.getChatMessageList().stream())
                .filter(chatMessage -> chatMessage != null)
                .filter(chatMessage -> chatMessage.getUsername().equals(recipientEmail))
                .filter(chatMessage -> chatMessage.getText() != null && chatMessage.getText().length() > 0)
                .findFirst().orElse(null);

        if(chatmessage != null)
            return chatmessage;
        else
            return new ChatMessage("","","Nie masz wiadomości","");

    }

    // Return reverse Stream
    private <T> Stream<T> reverse(Stream<T> stream)
    {
        LinkedList<T> stack = new LinkedList<>();
        stream.forEach(stack::push);

        return stack.stream();
    }

}
