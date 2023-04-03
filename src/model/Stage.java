package model;

import java.util.ArrayList;
import java.util.Calendar;

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
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setDurationInMonths(int amountMonths) {
        this.durationInMonths = amountMonths;
    }

    public void setStartPlannedDate(Calendar startPlannedDate) {
        this.startPlannedDate = startPlannedDate;
    }

    public void setEndPlannedDate(Calendar endPlannedDate) {
        this.endPlannedDate = endPlannedDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    //---------- Getters ----------
    
    //Stage
    public String getName() {
        return name;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public boolean getStatus() {
        return isActive;
    }

    public Calendar getStartPlannedDate() {
        return startPlannedDate;
    }

    public Calendar getEndPlannedDate() {
        return endPlannedDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }
    
    public Calendar getEndDate() {
        return endDate;
    }

    //Capsules
    public String[] getCapsuleTypes() {
        return capsuleTypes;
    }

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

    public void approveCapsule(int capsulePos) {
        Capsule capsule = capsulesUnderReview.get(capsulePos);

        capsule.setStatus("Approved");
        capsule.setApprovedDate();

        capsulesUnderReview.remove(capsulePos);
        capsulesApproved.add(capsule);
    }

    public String publicCapsule(int capsulePos) {
        Capsule capsule = capsulesApproved.get(capsulePos);

        String url = capsule.publicCapsule();

        capsule.setStatus("Published");

        capsulesApproved.remove(capsulePos);
        capsulesPublished.add(capsule);

        return url;
    }

}
