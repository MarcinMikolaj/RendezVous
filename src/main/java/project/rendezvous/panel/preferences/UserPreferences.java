package project.rendezvous.panel.preferences;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserPreferences {

    @Id
    private String id;
    private String email;
    private String gender;
    private int minAge;
    private int maxAge;
    private int distance;
    private String relationshipStatus;
    private String sexualOrientation;

    public UserPreferences() {
    }

    public UserPreferences(String email, String gender, int minAge, int maxAge, int distance, String relationshipStatus, String sexualOrientation) {
        this.email = email;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.distance = distance;
        this.relationshipStatus = relationshipStatus;
        this.sexualOrientation = sexualOrientation;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(String sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    @Override
    public String toString() {
        return "UserPreferences{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", distance=" + distance +
                ", relationshipStatus='" + relationshipStatus + '\'' +
                ", sexualOrientation='" + sexualOrientation + '\'' +
                '}';
    }
}
