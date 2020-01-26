package com.suman.kennelservice.model;

import java.io.Serializable;

public class MyDogCRUD implements Serializable {
    private String _id;
    private String petName;
    private String petType;
    private String Breed;
    private String Age;
    private String petSize;
    private String Gender;
    private String petvaccination;
    private String image;

    public MyDogCRUD( String  id, String petName, String petType, String breed, String age, String petSize, String gender, String petvaccination, String image) {
        this.petName = petName;
        this.petType = petType;
        Breed = breed;
        Age = age;
        this.petSize = petSize;
        Gender = gender;
        this.petvaccination = petvaccination;
        this.image = image;
        this._id = id;
    }

    public MyDogCRUD() {
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPetvaccination() {
        return petvaccination;
    }

    public void setPetvaccination(String petvaccination) {
        this.petvaccination = petvaccination;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }
}
