package model;

import java.util.ArrayList;

public class Capsule {
    private String id;
    private String description;
    private String type;
    private String lesson;

    private ArrayList <String> hashtags; 

    public Capsule(String id, String description, String type, String lesson) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.lesson = lesson;
        this.hashtags = new ArrayList<>();
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
    //------- Setters -------
    public void setHashtags(ArrayList<String> hashtags) {
        this.hashtags = hashtags;
    }

}
