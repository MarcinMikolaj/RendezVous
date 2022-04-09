package project.rendezvous.communicator.recipient;

import project.rendezvous.communicator.ChatMessage;
import project.rendezvous.panel.Picture;

// The object created from this class represents the candidate for the conversation
public class Recipient {

    // Candidate ID
    private String email;

    // Candidate nick
    private String nick;

    // Candidate photo
    private Picture profileImg;

    // Whether the candidate is currently logged in
    boolean isActive;

    // Last message send to user
    private ChatMessage lastMessage;

    public Recipient() {}

    public Recipient(String email, String nick, Picture profileImg, boolean isActive, ChatMessage lastMessage) {
        this.email = email;
        this.nick = nick;
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

    public ChatMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(ChatMessage lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "email='" + email + '\'' +
                ", nick='" + nick + '\'' +
                ", profileImg=" + profileImg +
                ", isActive=" + isActive +
                ", lastMessage=" + lastMessage +
                '}';
    }
}
