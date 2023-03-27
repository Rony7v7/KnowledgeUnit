package model;

import java.util.Calendar;

public class Project {
    
    private boolean isActive = true;
    private String name;
    private Calendar startPlannedDate; //Fechas reales ?
    private Calendar endPlannedDate;
    private double budget;
    private String[] clientData;

    private Employee[] collaborators = {new Employee("COLABORADOR 1", "COLLABORATOR"),
                                        new Employee("COLABORADOR 2", "COLLABORATOR"),
                                        new Employee("COLABORADOR 3", "COLLABORATOR"),
                                        new Employee("COLABORADOR 4", "COLLABORATOR"),
                                        new Employee("COLABORADOR 5", "COLLABORATOR")};

    private Employee[] managers;
    
    private Stage[] stages = {new Stage("INICIO",true),
                              new Stage("ANALISIS",false), 
                              new Stage("DISEÑO",false), 
                              new Stage("EJECUCION",false), 
                              new Stage("CIERRE Y SEGUIMIENTO",false), 
                              new Stage("CONTROL",false)};

    public Project(String name, Calendar startDate, Calendar endDate, Double budget, Employee[] managers, String[] clientData) {
        this.name = name;
        this.startPlannedDate = startDate;
        this.endPlannedDate = endDate;
        this.budget = budget;
        this.managers = managers;
        this.clientData = clientData;
    }


    //Setters
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

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

    public void setMonthsPerStage(int stagePosition, int durationInMonths) {
        boolean setted = false;

        for(int i = 0; i < stages.length && !setted; i++) {
            if(i == stagePosition) {
                stages[i].setDurationInMonths(durationInMonths);
                setted = true;
            }
        }
    }

    public void setStagePlannedStartDate(int stagePosition ,Calendar startPlannedDate) {
        boolean setted = false;

        for(int i = 0; i < stages.length && !setted; i++) {
            if(i == stagePosition) {
                stages[i].setStartPlannedDate(startPlannedDate);;
                setted = true;
            }
        }
    }

    public void setStagePlannedEndDate(int stagePosition, Calendar endPlannedDate) {
        boolean setted = false;

        for(int i = 0; i < stages.length && !setted; i++) {
            if(i == stagePosition) {
                stages[i].setEndPlannedDate(endPlannedDate);;
                setted = true;
            }
        }
    }

    public void setClientData(String[] clientData) {
        this.clientData = clientData;
    }
    //Getters
    public boolean getStatus() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public Calendar getStartPlannedDate() {
        return startPlannedDate;
    }

    public Calendar getEndPlannedDate() {
        return endPlannedDate;
    }

    public double getBudget() {
        return budget;
    }

    public String[] getClientData() {
        return clientData;
    }

    public Employee[] getCollaborators() {
        return collaborators;
    }

    public Employee[] getManagers() {
        return managers;
    }

    public String[] getStageNames() {
        String[] stageNames = {stages[0].getName(), stages[1].getName(), stages[2].getName(), stages[3].getName(),
                               stages[4].getName(),stages[5].getName()};
        return stageNames;
    }

    public String getStageActive() {
        boolean isActive = false;
        String stageName = "";

        for(int i = 0; i < stages.length && !isActive ; i++) {
            if(stages[i].getIsActive()) {
                stageName = stages[i].getName();
                isActive = true;
            }
        }
        return stageName;
    }
    //Aux
    public boolean closeStage() {
        boolean isSwitched = false;
        for(int i = 0; i < stages.length && !isSwitched ; i++) {
            //  Get stage active and verify if is not the last one
            if(getStageActive().equals(stages[i].getName()) && i < stages.length-1) {
                stages[i].setActive(false); //End Stage
                stages[i].setEndDate(Calendar.getInstance()); //Assign ending date

                stages[i+1].setActive(true); //Start next Stage
                stages[i+1].setStartDate(Calendar.getInstance()); // Assign starting date

                isSwitched = true; //Stop iteration

            } else if(i == stages.length-1) {
                setActive(false); //Project is inactivated
            }
        }
        return isSwitched;
    }
}
