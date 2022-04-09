package project.rendezvous.communicator.messages;

public class UpdateConversationRequestPojo {

    private String ownerEmail;
    private String recipientEmail;

    public UpdateConversationRequestPojo() {}

    public UpdateConversationRequestPojo(String ownerEmail, String recipientEmail) {
        this.ownerEmail = ownerEmail;
        this.recipientEmail = recipientEmail;
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

    @Override
    public String toString() {
        return "UpdateConversationRequestPojo{" +
                "ownerEmail='" + ownerEmail + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                '}';
    }
}
