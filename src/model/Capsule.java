package model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Capsule class
 */
public class Capsule {
    private String id;
    private String description;
    private String type;
    private String lesson;
    private String status;
    private Calendar approvedDate;

    private ArrayList <String> hashtags; 

    /**
     * Constructs a new Capsule object with the given parameters.
     *
     * @param id the id of the capsule
     * @param description the description of the capsule
     * @param type the type of the capsule
     * @param lesson the lesson of the capsule
     * @param hashtags the hashtags associated with the capsule
     */
    public Capsule(String id, String description, String type, String lesson, ArrayList<String> hashtags) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.lesson = lesson;
        this.hashtags = hashtags;
        this.status = "UNDER REVIEW";
    }

    //Getters
    /**
     * Returns the id of the capsule.
     *
     * @return the id of the capsule
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the description of the capsule.
     *
     * @return the description of the capsule
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the capsule.
     *
     * @return the type of the capsule
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the lesson of the capsule.
     *
     * @return the lesson of the capsule
     */    
    public String getLesson() {
        return lesson;
    }

    /**
     * Returns the status of the capsule.
     *
     * @return the status of the capsule
     */    
    public String getStatus() {
        return status;
    }

    /**
     * Returns the approved date of the capsule.
     *
     * @return the approved date of the capsule
     */    
    public Calendar getApprovedDate() {
        return approvedDate;
    }


    /**
     * Returns the hashtags associated with the capsule.
     *
     * @return the hashtags associated with the capsule
     */    
    public ArrayList<String> getHashtags() {
        return hashtags;
    }

    /**
     * Returns the information of the capsule in a formatted string.
     *
     * @return the information of the capsule
     */
    public String getInfo() {
        return id+" | "+type+" | "+description+"\n- "+lesson;
    }

    //Setters
    /**
     * Sets the status of the capsule.
     *
     * @param status the new status of the capsule
     */    
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the approved date of the capsule to the current date and time.
     */    
    public void setApprovedDate() {
        this.approvedDate = Calendar.getInstance();
    }

    //AUX
    /**
     * Returns the URL of the public version of the capsule.
     *
     * @return the URL of the public version of the capsule
     */    
    public String publicCapsule() {
        return "https://www.greensqa.com/"+id;
    }

}
