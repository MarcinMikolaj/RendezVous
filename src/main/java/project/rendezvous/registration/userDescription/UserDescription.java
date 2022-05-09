package project.rendezvous.registration.userDescription;

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

    private String work;
    private String university;
    private String city;
    private int kilometersAway; // Set only when sending the object to the client

    private String haveChildren;
    private String amISmoking;
    private String doIDrinkAlcohol;
    private String howTallAmI;
    private String howMuchDoIWeight;
    private String doIPlaySports;

    private String aboutMeDescription;
    private String interested;

    private int numberOfImg = 0;
    private List<String> pathToImgList = new ArrayList<String>();

    // Set only when sending the object to the client
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

    public UserDescription(String email, String name, String gender, int age, String sexualOrientation, String relationshipStatus,
                           String work, String university, String city, int kilometersAway, String haveChildren, String amISmoking,
                           String doIDrinkAlcohol, String howTallAmI, String howMuchDoIWeight, String doIPlaySports, String aboutMeDescription,
                           String interested, int numberOfImg, List<String> pathToImgList, List<Picture> pictures) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.sexualOrientation = sexualOrientation;
        this.relationshipStatus = relationshipStatus;
        this.work = work;
        this.university = university;
        this.city = city;
        this.kilometersAway = kilometersAway;
        this.haveChildren = haveChildren;
        this.amISmoking = amISmoking;
        this.doIDrinkAlcohol = doIDrinkAlcohol;
        this.howTallAmI = howTallAmI;
        this.howMuchDoIWeight = howMuchDoIWeight;
        this.doIPlaySports = doIPlaySports;
        this.aboutMeDescription = aboutMeDescription;
        this.interested = interested;
        this.numberOfImg = numberOfImg;
        this.pathToImgList = pathToImgList;
        this.pictures = pictures;
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

    public String getHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(String haveChildren) {
        this.haveChildren = haveChildren;
    }

    public String getAmISmoking() {
        return amISmoking;
    }

    public void setAmISmoking(String amISmoking) {
        this.amISmoking = amISmoking;
    }

    public String getDoIDrinkAlcohol() {
        return doIDrinkAlcohol;
    }

    public void setDoIDrinkAlcohol(String doIDrinkAlcohol) {
        this.doIDrinkAlcohol = doIDrinkAlcohol;
    }

    public String getHowTallAmI() {
        return howTallAmI;
    }

    public void setHowTallAmI(String howTallAmI) {
        this.howTallAmI = howTallAmI;
    }

    public String getHowMuchDoIWeight() {
        return howMuchDoIWeight;
    }

    public void setHowMuchDoIWeight(String howMuchDoIWeight) {
        this.howMuchDoIWeight = howMuchDoIWeight;
    }

    public String getDoIPlaySports() {
        return doIPlaySports;
    }

    public void setDoIPlaySports(String doIPlaySports) {
        this.doIPlaySports = doIPlaySports;
    }

    public String getInterested() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested = interested;
    }

    public String toStringShort() {
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
                ", work='" + work + '\'' +
                ", university='" + university + '\'' +
                ", city='" + city + '\'' +
                ", kilometersAway=" + kilometersAway +
                ", haveChildren='" + haveChildren + '\'' +
                ", amISmoking='" + amISmoking + '\'' +
                ", doIDrinkAlcohol='" + doIDrinkAlcohol + '\'' +
                ", howTallAmI='" + howTallAmI + '\'' +
                ", howMuchDoIWeight='" + howMuchDoIWeight + '\'' +
                ", doIPlaySports='" + doIPlaySports + '\'' +
                ", aboutMeDescription='" + aboutMeDescription + '\'' +
                ", interested='" + interested + '\'' +
                ", numberOfImg=" + numberOfImg +
                ", pathToImgList=" + pathToImgList +
                ", pictures=" + pictures +
                '}';
    }
}
