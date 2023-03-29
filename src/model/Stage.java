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

    private Capsule[] capsules = new Capsule[50];


    public Stage(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
        this.startDate = Calendar.getInstance();
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    //Setters
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

    //Getters
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

}
