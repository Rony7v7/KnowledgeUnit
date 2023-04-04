package model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Stage Class
 */
public class Stage {
    private String name;
    private boolean isActive;
    private int durationInMonths;
    private Calendar startPlannedDate;
    private Calendar endPlannedDate;
    private Calendar startDate;
    private Calendar endDate;

    private Capsule[] capsules;
    private ArrayList <Capsule> capsulesUnderReview;
    private ArrayList <Capsule> capsulesApproved;
    private ArrayList <Capsule> capsulesPublished;
    private int amountCapsules;
    private String[] capsuleTypes;


    /**
     * Constructor for Stage class.
     * 
     * @param name The name of the stage.
     * @param isActive Whether the stage is active or not.
     */
    public Stage(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
        this.startDate = Calendar.getInstance();
        this.amountCapsules = 0;

        this.capsules = new Capsule[50];
        this.capsuleTypes = new String[] {"TÉCNICO","GESTIÓN","DOMINIO","EXPERIENCIAS"};
        this.capsulesUnderReview = new ArrayList<>();
        this.capsulesApproved = new ArrayList<>();
        this.capsulesPublished = new ArrayList<>();
    }

    //---------- Setters ----------
    /**
     * Sets whether the stage is active or not.
     * 
     * @param isActive Whether the stage is active or not.
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Sets the duration in months of the stage.
     * 
     * @param amountMonths The duration in months of the stage.
     */
    public void setDurationInMonths(int amountMonths) {
        this.durationInMonths = amountMonths;
    }

    /**
     * Sets the planned start date of the stage.
     * 
     * @param startPlannedDate The planned start date of the stage.
     */
    public void setStartPlannedDate(Calendar startPlannedDate) {
        this.startPlannedDate = startPlannedDate;
    }

    /**
     * Sets the planned end date of the stage.
     * 
     * @param endPlannedDate The planned end date of the stage.
     */
    public void setEndPlannedDate(Calendar endPlannedDate) {
        this.endPlannedDate = endPlannedDate;
    }

    /**
     * Set the start date of the project
     * 
     * @param startDate the start date to set
    */
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the actual end date of the stage.
     * 
     * @param endDate The actual end date of the stage.
     */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }


    //---------- Getters ----------
    
    //Stage
    /**
     * Gets the name of the stage.
     * 
     * @return The name of the stage.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the duration of the stage in months.
     * 
     * @return The duration of the stage in months.
     */
    public int getDurationInMonths() {
        return durationInMonths;
    }

    /**
     * Gets the status of the stage.
     * 
     * @return Whether the stage is active or not.
     */    
    public boolean getStatus() {
        return isActive;
    }

    /**
     * Gets the planned start date of the stage.
     * 
     * @return The planned start date of the stage.
     */
    public Calendar getStartPlannedDate() {
        return startPlannedDate;
    }

    /**
     * Gets the planned end date of the stage.
     * 
     * @return The planned end date of the stage.
     */
    public Calendar getEndPlannedDate() {
        return endPlannedDate;
    }


    /**
     * Gets the actual end date of the stage.
     * 
     * @return The actual end date of the stage.
     */
    public Calendar getStartDate() {
        return startDate;
    }
    
    /**
     * Gets the actual end date of the stage.
     * 
     * @return The actual end date of the stage.
     */
    public Calendar getEndDate() {
        return endDate;
    }

    //Capsules
    
    /**
     * Gets the types of capsules available.
     * 
     * @return The types of capsules available.
     */
    public String[] getCapsuleTypes() {
        return capsuleTypes;
    }

    /**
     * Gets the number of capsules with the specified status.
     * 
     * @param status The status of the capsules to count. 0 for all, 1 for under review, 2 for approved, 3 for published.
     * @return The number of capsules with the specified status.
     */
    public int getAmountCapsules(int status) {  // 0 -> all ; 1 -> under Review; 2 -> Approved ; 3-> published
        int amount = 0;

        switch (status) {
            case 0: amount = amountCapsules;
                break;
            case 1: amount = capsulesUnderReview.size();
                break;
            case 2: amount = capsulesApproved.size();
                break;
            case 3: amount = capsulesPublished.size();
                break;
        }
        
        return amount;
    }

    /**
     * Gets the information of the capsule at the specified position and with the specified status.
     * 
     * @param capsulePosition The position of the capsule to get information for.
     * @param capsuleStatus The status of the capsule to get information for. 0 for all, 1 for under review, 2 for approved, 3
     * @return capsuleInfo  The information of the capsule choosed.
     */
    public String getCapsuleInfo(int capsulePosition, int capsuleStatus) {  // 0 -> all ; 1 -> under Review; 2 -> Approved ; 3-> published
        String capsuleInfo = "";
        switch (capsuleStatus) {
            case 0: capsuleInfo = capsules[capsulePosition].getInfo();
                break;
            case 1: capsuleInfo = capsulesUnderReview.get(capsulePosition).getInfo();
                break;
            case 2: capsuleInfo = capsulesApproved.get(capsulePosition).getInfo();
                break;
            case 3: capsuleInfo = capsulesPublished.get(capsulePosition).getInfo();
        }
        return capsuleInfo;
    }

    
    //AUX
    /**
     * Adds a new capsule to the capsules array.
     * 
     * @param id The ID of the capsule to be added.
     * @param description The description of the capsule.
     * @param type The type of the capsule.
     * @param lesson The lesson the capsule is associated with.
     * @param hashtags An ArrayList of hashtags associated with the capsule.
     * @return A message indicating if the capsule was added successfully or if the maximum number of capsules has been reached.
    */
    public String addCapsule(String id, String description, String type, String lesson, ArrayList<String> hashtags) {
        String msg = "\nCapacidad máxima (50) alcanzada.";

        if(amountCapsules < capsules.length) {

            capsules[amountCapsules] = new Capsule(id, description, type, lesson, hashtags);
            capsulesUnderReview.add(capsules[amountCapsules]);
            amountCapsules ++;
            
            msg = "\nCapsula registrada con exito.";
        }

        return msg;
    }

    /**
     * Approves a capsule.
     * 
     * @param capsulePos The position of the capsule in the capsulesUnderReview ArrayList.
    */
    public void approveCapsule(int capsulePos) {
        Capsule capsule = capsulesUnderReview.get(capsulePos);

        capsule.setStatus("Approved");
        capsule.setApprovedDate();

        capsulesUnderReview.remove(capsulePos);
        capsulesApproved.add(capsule);
    }

    /**
     * Publishes a capsule.
     * @param capsulePos The position of the capsule in the capsulesApproved ArrayList.
     * @return The URL of the published capsule.
    */
    public String publicCapsule(int capsulePos) {
        Capsule capsule = capsulesApproved.get(capsulePos);

        String url = capsule.publicCapsule();

        capsule.setStatus("Published");

        capsulesApproved.remove(capsulePos);
        capsulesPublished.add(capsule);

        return url;
    }

}
