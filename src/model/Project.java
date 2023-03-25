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

}
