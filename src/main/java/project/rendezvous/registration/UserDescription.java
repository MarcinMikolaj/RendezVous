package project.rendezvous.registration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import project.rendezvous.constraints.Email;
import project.rendezvous.panel.Picture;

import java.util.ArrayList;
import java.util.List;

@Document
public class UserDescription {

    @Id
    private String id;
    @Email
    private String email;
    private String name;
    private String gender;
    private int age;
    private String sexualOrientation;
    private String relationshipStatus;
    private String aboutMeDescription;

    private int numberOfImg = 0;
    private List<String> pathToImgList = new ArrayList<String>();
    private List<Picture> pictures = new ArrayList<Picture>();

    public UserDescription() {
    }

    public UserDescription(String email, String name, String gender, int age, String sexualOrientation, String relationshipStatus, String aboutMeDescription) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.sexualOrientation = sexualOrientation;
        this.relationshipStatus = relationshipStatus;
        this.aboutMeDescription = aboutMeDescription;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(String sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getAboutMeDescription() {
        return aboutMeDescription;
    }

    public void setAboutMeDescription(String aboutMeDescription) {
        this.aboutMeDescription = aboutMeDescription;
    }

    public List<String> getPathToImgList() {
        return pathToImgList;
    }

    public void setPathToImgList(List<String> pathToImgList) {
        this.pathToImgList = pathToImgList;
    }

    public int getNumberOfImg() {
        return numberOfImg;
    }

    public void setNumberOfImg(int numberOfImg) {
        this.numberOfImg = numberOfImg;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "UserDescription{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", sexualOrientation='" + sexualOrientation + '\'' +
                ", relationshipStatus='" + relationshipStatus + '\'' +
                ", aboutMeDescription='" + aboutMeDescription + '\'' +
                '}';
    }
}
