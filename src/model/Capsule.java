package model;

import java.util.ArrayList;

public class Capsule {
    private String id;
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
    
}
