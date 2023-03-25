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
    public void setDurationInMonths(int amountMonths) {
        this.durationInMonths = amountMonths;
    }

    //Getters
    public int getDurationInMonths() {
        return durationInMonths;
    }

    public boolean getIsActive() {
        return isActive;
    }


}
