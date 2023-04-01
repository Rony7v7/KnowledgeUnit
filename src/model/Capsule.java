package model;

public class Capsule {
    private String id;
    private String description;
    private String type;
    private String lesson;

    private String[] hashtags; 

    public Capsule(String id, String description, String type, String lesson) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.lesson = lesson;
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

    //------- Setters -------
    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }

}
