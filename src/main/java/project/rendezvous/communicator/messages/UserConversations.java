package project.rendezvous.communicator.messages;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class UserConversations {

    @Id
    private String id;

    // Specify who the conversation belongs to
    private String email;

    // A list of all the user's conversations
    private List<Conversation> conversationList = new ArrayList<Conversation>();

    public UserConversations() {}

    public UserConversations(String email) {
        this.email = email;
    }

    public UserConversations(String email, List<Conversation> conversationList) {
        this.email = email;
        this.conversationList = conversationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }

    @Override
    public String toString() {
        return "UserConversations{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", conversationList=" + conversationList +
                '}';
    }
}
