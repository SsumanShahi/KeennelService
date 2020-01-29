package com.suman.kennelservice.model;

public class Training {

    private String image;
    private String trainingName;
    private String steps;

    public Training(String image, String trainingName, String steps) {
        this.image = image;
        this.trainingName = trainingName;
        this.steps = steps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
