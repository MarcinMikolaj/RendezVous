package project.rendezvous.communicator.messages;

import project.rendezvous.communicator.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class Conversation {

    private String ownerEmail;
    private String recipientEmail;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();

    public Conversation() {}

    public Conversation(String ownerEmail, String recipientEmail, List<ChatMessage> chatMessageList) {
        this.ownerEmail = ownerEmail;
        this.recipientEmail = recipientEmail;
        this.chatMessageList = chatMessageList;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "ownerEmail='" + ownerEmail + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", chatMessageList=" + chatMessageList +
                '}';
    }
}
