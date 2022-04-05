package project.rendezvous.communicator.recipient;

import project.rendezvous.panel.Picture;

// The object created from this class represents the candidate for the conversation
public class Recipient {

    // Candidate ID
    private String email;

    // Candidate photo
    private Picture profileImg;

    // Whether the candidate is currently logged in
    boolean isActive;

    // Last message send to user
    private Message lastMessage;

    public Recipient() {}

    public Recipient(String email, Picture profileImg, boolean isActive, Message lastMessage) {
        this.email = email;
        this.profileImg = profileImg;
        this.isActive = isActive;
        this.lastMessage = lastMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Picture getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(Picture profileImg) {
        this.profileImg = profileImg;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "email='" + email + '\'' +
                ", profileImg=" + profileImg +
                ", isActive=" + isActive +
                ", lastMessage=" + lastMessage +
                '}';
    }
}
