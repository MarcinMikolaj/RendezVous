package project.rendezvous.registration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import project.rendezvous.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Document
public class UserFriends {

    @Id
    private String id;

    @Email
    private String email;
    private List<String> likeUsers = new ArrayList<String>();
    private List<String> rejectedUsers = new ArrayList<String>();
    private List<String> likeAndRejectUsers = new ArrayList<String>();
    private List<String> formedPairsWithUsersList = new ArrayList<String>();

    public UserFriends() {}

    public UserFriends(String email) {
        this.email = email;
    }

    public UserFriends(String email, List<String> likeUsers, List<String> rejectedUsers, List<String> likeAndRejectUsers, List<String> formedPairsWithUsersList) {
        this.email = email;
        this.likeUsers = likeUsers;
        this.rejectedUsers = rejectedUsers;
        this.likeAndRejectUsers = likeAndRejectUsers;
        this.formedPairsWithUsersList = formedPairsWithUsersList;
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

    public List<String> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<String> likeUsers) {
        this.likeUsers = likeUsers;
    }

    public List<String> getRejectedUsers() {
        return rejectedUsers;
    }

    public void setRejectedUsers(List<String> rejectedUsers) {
        this.rejectedUsers = rejectedUsers;
    }

    public List<String> getLikeAndRejectUsers() {
        return likeAndRejectUsers;
    }

    public void setLikeAndRejectUsers(List<String> likeAndRejectUsers) {
        this.likeAndRejectUsers = likeAndRejectUsers;
    }

    public List<String> getFormedPairsWithUsersList() {
        return formedPairsWithUsersList;
    }

    public void setFormedPairsWithUsersList(List<String> formedPairsWithUsersList) {
        this.formedPairsWithUsersList = formedPairsWithUsersList;
    }

    @Override
    public String toString() {
        return "UserFriends{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", likeUsers=" + likeUsers +
                ", rejectedUsers=" + rejectedUsers +
                ", likeAndRejectUsers=" + likeAndRejectUsers +
                ", formedPairsWithUsersList=" + formedPairsWithUsersList +
                '}';
    }
}
