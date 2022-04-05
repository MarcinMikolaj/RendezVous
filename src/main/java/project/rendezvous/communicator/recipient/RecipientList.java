package project.rendezvous.communicator.recipient;

import java.util.ArrayList;
import java.util.List;

// Based on the class, an object is created from which you can retrieve recipients for conversation
// This object is sent to the client upon receipt of a request for candidates for conversion
public class RecipientList {

    // Identifier of the user to whom the list is sent
    private String userEmail;

    // List of recipients to conduct the conversation
    private List<Recipient> recipientList = new ArrayList<Recipient>();

    public RecipientList() {}

    public RecipientList(String userEmail, List<Recipient> recipientList) {
        this.userEmail = userEmail;
        this.recipientList = recipientList;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Recipient> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<Recipient> recipientList) {
        this.recipientList = recipientList;
    }

    @Override
    public String toString() {
        return "RecipientList{" +
                "userEmail='" + userEmail + '\'' +
                ", recipientList=" + recipientList +
                '}';
    }
}
