package com.suman.kennelservice.model;

public class Appointment {
    private String OwnerName;
    private String petname;
    private String Breed;
    private String Age;
    private String Gender;

    public Appointment(String ownerName, String petname, String breed, String age, String gender) {
        OwnerName = ownerName;
        this.petname = petname;
        Breed = breed;
        Age = age;
        Gender = gender;
    }

    public Appointment() {
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
