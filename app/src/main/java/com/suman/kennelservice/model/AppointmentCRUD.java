package com.suman.kennelservice.model;

import java.io.Serializable;

public class AppointmentCRUD implements Serializable {
    private String _id;
    private String OwnerName;
    private String petname;
    private String Breed;
    private String Age;
    private String Gender;

    public AppointmentCRUD() {
    }

    public AppointmentCRUD(String _id, String ownerName, String petname, String breed, String age, String gender) {
        this._id = _id;
        OwnerName = ownerName;
        this.petname = petname;
        Breed = breed;
        Age = age;
        Gender = gender;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
