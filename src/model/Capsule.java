package model;

import java.util.ArrayList;

public class Capsule {
    private String id; //Id's diferentes por proytecto? por etapa? 
    private String description;
    private String type;
    private String lesson;
    private String status;

    private ArrayList <String> hashtags; 

    public Capsule(String id, String description, String type, String lesson, ArrayList<String> hashtags) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.lesson = lesson;
        this.hashtags = hashtags;
        this.status = "UNDER REVIEW";
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getLesson() {
        return lesson;
    }

    public ArrayList<String> getHashtags() {
        return hashtags;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getInfo() {
        return id+" | "+type+" | "+description+"\n- "+lesson;
    }

    //Setters
    public void setStatus(String status) {
        this.status = status;
    }

}
