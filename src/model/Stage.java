package model;

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
    private int amountCapsules = 0;
    private String[] capsuleTypes;


    public Stage(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
        this.startDate = Calendar.getInstance();

        this.capsules = new Capsule[50];
        this.capsuleTypes = new String[] {"TÉCNICO","GESTIÓN","DOMINIO","EXPERIENCIAS"};
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

    public int getAmountCapsules() {
        return amountCapsules;
    }

    //AUX
    public String addCapsule(Capsule capsule) {
        String msg = "\nCapacidad máxima (50) alcanzada.";

        if(amountCapsules < capsules.length) {
            amountCapsules ++;
            capsules[amountCapsules] = capsule;
            msg = "\nCapsula registrada con exito.";
        }

        return msg;
    }

}
