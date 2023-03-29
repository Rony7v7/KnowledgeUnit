package model;

public class Capsule {
    private String id;
    private String description;
    private String type;
    private String lesson;
    private String content;

    public Capsule(String id, String description, String type, String lesson, String content) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.lesson = lesson;
        this.content = content;
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
}
