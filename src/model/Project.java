package model; //HERENCIA

import java.util.ArrayList;
import java.util.Calendar;

public class Project {
    
    private boolean isActive = true;
    private String name;
    private Calendar startPlannedDate;
    private Calendar endPlannedDate;
    private Calendar endDate;
    private double budget;
    private String[] clientData;

    private Employee[] collaborators;

    private Employee[] managers; //Herencia?
    
    private Stage[] stages;

    public Project(String name, Calendar startDate, Calendar endDate, Double budget, Employee[] managers, String[] clientData) {
        this.name = name;
        this.startPlannedDate = startDate;
        this.endPlannedDate = endDate;
        this.budget = budget;
        this.managers = managers;
        this.clientData = clientData;

        this.stages = new Stage[]  {new Stage("INICIO",true),
                                    new Stage("ANALISIS",false), 
                                    new Stage("DISEÑO",false), 
                                    new Stage("EJECUCION",false), 
                                    new Stage("CIERRE Y SEGUIMIENTO",false), 
                                    new Stage("CONTROL",false)};

        this.collaborators = new Employee[] {new Employee("COLABORADOR 1", "COLLABORATOR"),
                                             new Employee("COLABORADOR 2", "COLLABORATOR"),
                                             new Employee("COLABORADOR 3", "COLLABORATOR"),
                                             new Employee("COLABORADOR 4", "COLLABORATOR"),
                                             new Employee("COLABORADOR 5", "COLLABORATOR")};
    }

    //--------- Setters ----------

    //Project
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

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public void setClientData(String[] clientData) {
        this.clientData = clientData;
    }
    

    //Stages
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

    //---------- Getters -----------

    //Project
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

    public Calendar getEndDate() {
        return endDate;
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

    //Stages
    public String[] getStageNames() {
        String[] stageNames = {stages[0].getName(), stages[1].getName(), stages[2].getName(), stages[3].getName(),
                               stages[4].getName(),stages[5].getName()};
        return stageNames;
    }

    public String getStageActiveName() {
        boolean isActive = false;
        String stageName = "";

        for(int i = 0; i < stages.length && !isActive ; i++) {
            if(stages[i].getStatus()) {
                stageName = stages[i].getName();
                isActive = true;
            }
        }
        return stageName;
    }

    //Capsules
    public String[] getCapsuleTypes() {
        return stages[0].getCapsuleTypes();
    }

    public String[][] getCapsulesInfo(int capsulesStatus) {
        String[][] capsulesInfoPerStage = new String[stages.length][];
        String [] capsulesInfo;
        int amountCapsules = 0; 

        for(int i = 0; i < stages.length; i++) {
            amountCapsules = stages[i].getAmountCapsules(capsulesStatus);
            
            if(amountCapsules != 0){
                capsulesInfo = new String[amountCapsules];

                for(int j = 0 ; j < amountCapsules ; j++) {
                    capsulesInfo[j] = stages[i].getCapsuleInfo(j,capsulesStatus);
                }
                capsulesInfoPerStage[i] = capsulesInfo;

            } else {
                capsulesInfoPerStage[i] = null; 
            }
        }

        return capsulesInfoPerStage;
    }

    public int getAmountCapsules(int status) { 
        int amountCapsules = 0; 
        for(int i = 0; i < stages.length; i++) {
            amountCapsules += stages[i].getAmountCapsules(status);
        }
        return amountCapsules;
    }

    //Employee
    public String[] getCollaboratorNames() {
        String [] collaboratorNames = new String[collaborators.length];

        for(int i = 0; i < collaborators.length ; i++) {
            collaboratorNames[i] = collaborators[i].getName();
        }

        return collaboratorNames;
    }

    //--------- AUX
    public boolean closeStage() {
        boolean stillActive = false;
        for(int i = 0; i < stages.length && !stillActive ; i++) {
            //  if there is a stage active and if is not the last one
            if(getStageActiveName().equals(stages[i].getName()) && i < stages.length-1) {
                Calendar currentDate = Calendar.getInstance();

                stages[i].setActive(false); //End Stage
                stages[i].setEndDate(currentDate); //Assign ending date

                stages[i+1].setActive(true); //Start next Stage
                stages[i+1].setStartDate(currentDate); // Assign starting date

                stillActive = true; //Stop iteration

            // else if the stage is the last one and the project is active
            } else if(i == stages.length-1 && getStatus()) {
                stages[i].setActive(false); // end the last stage
                stages[i].setEndDate(Calendar.getInstance()); //Assign ending date

                setActive(false); //Project is inactivated
                setEndDate(Calendar.getInstance()); //Set project ending date
            }
        }
        return stillActive;
    }

    public String registerCapsule(int collaboratorPos, String description, int type, String lesson) {
        Capsule capsule;
        Stage stageActive = stages[0];
        String id;
        String typeName;
        String msg = "\nNo hay etapas activas";

        boolean isFound = false;
        
        //Search stage active
        for(int i = 0; i < stages.length && !isFound ; i++) {
            if(stages[i].getStatus()) {
                stageActive = stages[i] ;
                isFound = true;
            }
        }

        //Set id
        id = "CC"+(name+stageActive.getName()).hashCode()+stageActive.getAmountCapsules(0);
        //Set Type
        typeName = getCapsuleTypes()[type];
        

        //SET #'s 
        ArrayList <String> hashtags = assignHashtags(description,lesson);
    
        if(hashtags.contains(null)) {
            msg = "\nRegistro fallido.\nEn la descripción y en la lección deben haber palabras clave (Ejemplo #Pruebas Funcionales#).";
        } else {
            msg = stageActive.addCapsule(id, description, typeName, lesson, hashtags);
            collaborators[collaboratorPos].addCapsule(id);
        }
        
        return msg;
    }

    public ArrayList<String> assignHashtags(String description, String lesson) {
        ArrayList<String> hashtags = new ArrayList<>();
        String keyword = "";

        String[] texts = {description, lesson};
        String[] textSplit;
        
        //Split the 2 texts
        for(int i = 0 ; i < texts.length ; i++ ) {
            textSplit = texts[i].split("#");
            if(textSplit.length == 1) {
                hashtags.add(null);
            }

            //separate hashtags
            for(int j = 1 ; j < textSplit.length ; j += 2) {
                keyword = textSplit[j];
                hashtags.add(keyword);
            }
        }

        return hashtags;
    }

    public void approveCapsule(int capsulePos) {
        int count = 0;
        boolean isFound = false;
        
        //Search 
        for(int i = 0; i < stages.length && !isFound ; i++) {

            if(stages[i].getAmountCapsules(1) > 0) {
                for(int j = 0; j < stages[i].getAmountCapsules(1) && !isFound; j++) {

                    if(count == capsulePos) {
                        stages[i].approveCapsule(j);
                        isFound = true;
                    }
                    count++;
                    
                }
            }
        }
    }

    public String publicCapsule(int capsulePos) {
        int count = 0;
        boolean isFound = false;
        String msg = "NADA";
        
        //Search 
        for(int i = 0; i < stages.length && !isFound ; i++) {
            if(stages[i].getAmountCapsules(2) > 0) {

                for(int j = 0; j < stages[i].getAmountCapsules(2) && !isFound; j++) {
                    if(count == capsulePos) {
                        msg = stages[i].publicCapsule(j);
                        isFound = true;
                    }
                    count++;
                    
                }
            }
        }
    
        return msg;
    }

}
