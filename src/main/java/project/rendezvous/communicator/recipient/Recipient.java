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

    private String name;
    private int age;
    private String work;
    private String university;
    private String city;
    private String aboutMeDescription;
    private String interested;
    // Set only when sending the object to the client
    private int kilometersAway;

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

    public Recipient(String email, String nick, Picture profileImg, String name, int age, String work, String university, String city, String aboutMeDescription, String interested, int kilometersAway, boolean isActive, ChatMessage lastMessage) {
        this.email = email;
        this.nick = nick;
        this.profileImg = profileImg;
        this.name = name;
        this.age = age;
        this.work = work;
        this.university = university;
        this.city = city;
        this.aboutMeDescription = aboutMeDescription;
        this.interested = interested;
        this.kilometersAway = kilometersAway;
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

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getKilometersAway() {
        return kilometersAway;
    }

    public void setKilometersAway(int kilometersAway) {
        this.kilometersAway = kilometersAway;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAboutMeDescription() {
        return aboutMeDescription;
    }

    public void setAboutMeDescription(String aboutMeDescription) {
        this.aboutMeDescription = aboutMeDescription;
    }

    public String getInterested() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested = interested;
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
