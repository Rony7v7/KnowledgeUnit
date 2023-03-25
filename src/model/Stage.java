package model;

public class Stage {
    private String name;
    private boolean isActive;
    private int durationInMonths;

    public Stage(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int amountMonths) {
        this.durationInMonths = amountMonths;
    }

}
