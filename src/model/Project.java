package model;

import java.util.Calendar;

public class Project {
    
    private String name;
    private Calendar startPlannedDate;
    private Calendar endPlannedDate;
    private double budget;
    private Manager[] projectManagers;
    private Client client;

    private Stage[] stages = {new Stage("INICIO",true),
                              new Stage("ANALISIS",false), 
                              new Stage("DISEÑO",false), 
                              new Stage("EJECUCION",false), 
                              new Stage("CIERRE Y SEGUIMIENTO",false), 
                              new Stage("CONTROL",false)};

    public Project(String name, Calendar startDate, Calendar endDate, Double budget, Manager[] managers, Client client) {
        this.name = name;
        this.startPlannedDate = startDate;
        this.endPlannedDate = endDate;
        this.budget = budget;
        this.projectManagers = managers;
        this.client = client;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setStartPlannedDate(Calendar startPlannedDate) {
        this.startPlannedDate = startPlannedDate;
    }

    public void setEndPlannedDate(Calendar endPlannedDate) {
        this.endPlannedDate = endPlannedDate;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setMonthsPerStage(int stage1, int stage2, int stage3, int stage4, int stage5, int stage6) {
        stages[0].setDurationInMonths(stage1);
        stages[1].setDurationInMonths(stage2);
        stages[2].setDurationInMonths(stage3);
        stages[3].setDurationInMonths(stage4);
        stages[4].setDurationInMonths(stage5);
        stages[5].setDurationInMonths(stage6);
    }


    //Getters
    public String[] getStageNames() {
        String[] stageNames = {stages[0].getName(), stages[1].getName(), stages[2].getName(), stages[3].getName(),
                               stages[4].getName(),stages[5].getName()};
        return stageNames;
    }

}
