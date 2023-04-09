package model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Project class
 */
public class Project {
    
    private boolean isActive = true;
    private String name;
    private Calendar startPlannedDate;
    private Calendar endPlannedDate;
    private Calendar endDate;
    private double budget;
    private String[] clientData;

    private Employee[] collaborators;

    private Employee[] managers;
    
    private Stage[] stages;

    /**
     * Constructs a new Project object with the specified name, start date, end date, budget, managers, and client data.
     * 
     * @param name the name of the project
     * @param startDate the planned start date of the project
     * @param endDate the planned end date of the project
     * @param budget the budget allocated for the project
     * @param managers an array of managers assigned to the project
     * @param clientData an array of client data associated with the project
     */
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

        this.collaborators = new Employee[] {new Employee("COLABORADOR 1", "COLLABORATOR",null),
                                             new Employee("COLABORADOR 2", "COLLABORATOR",null),
                                             new Employee("COLABORADOR 3", "COLLABORATOR",null),
                                             new Employee("COLABORADOR 4", "COLLABORATOR",null),
                                             new Employee("COLABORADOR 5", "COLLABORATOR",null)};
    }

    //--------- Setters ----------

    //Project
    /**
    *Set the status of the project.
    *@param isActive a boolean value that represents if the project is active or not. 
    */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
    * Set the name of the project.
    *
    * @param name the name of the project to set.
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * Set the planned start date of the project.
    *
    * @param startPlannedDate the planned start date of the project to set.
    */
    public void setStartPlannedDate(Calendar startPlannedDate) {
        this.startPlannedDate = startPlannedDate;
    }

    /**
    * Set the planned end date of the project.
    *
    * @param endPlannedDate the planned end date of the project to set.
    */
    public void setEndPlannedDate(Calendar endPlannedDate) {
        this.endPlannedDate = endPlannedDate;
    }

    /**
    * Set the budget of the project.
    *
    * @param budget the budget of the project to set.
    */
    public void setBudget(double budget) {
        this.budget = budget;
    }

    /**
    * Set the end date of the project.
    *
    * @param endDate the end date of the project to set.
    */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    /**
    * Set the client data of the project.
    *
    * @param clientData the client data of the project to set.
    */
    public void setClientData(String[] clientData) {
        this.clientData = clientData;
    }
    

    //Stages
    /**
    * Set the duration of the specified stage in months.
    *
    * @param stagePosition the position of the stage to set the duration for.
    * @param durationInMonths the duration of the stage to set.
    */
    public void setMonthsPerStage(int stagePosition, int durationInMonths) {
        boolean setted = false;

        for(int i = 0; i < stages.length && !setted; i++) {
            if(i == stagePosition) {
                stages[i].setDurationInMonths(durationInMonths);
                setted = true;
            }
        }
    }

    /**
    * Set the planned start date of the specified stage.
    *
    * @param stagePosition the position of the stage to set the planned start date for.
    * @param startPlannedDate the planned start date of the stage to set.
    */
    public void setStagePlannedStartDate(int stagePosition ,Calendar startPlannedDate) {
        boolean setted = false;

        for(int i = 0; i < stages.length && !setted; i++) {
            if(i == stagePosition) {
                stages[i].setStartPlannedDate(startPlannedDate);;
                setted = true;
            }
        }
    }

    /**
    * Set the planned end date of the specified stage.
    *
    * @param stagePosition the position of the stage to set the planned end date for.
    * @param endPlannedDate the planned end date of the stage to set.
    */
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
    /**
    * Get the status of the project.
    *
    * @return the status of the project.
    */
    public boolean getStatus() {
        return isActive;
    }

    /**
    * Get the name of the project.
    *
    * @return the name of the project.
    */
    public String getName() {
        return name;
    }

    /**
    * Get the planned start date of the project.
    *
    * @return the planned start date of the project.
    */
    public Calendar getStartPlannedDate() {
        return startPlannedDate;
    }

    /**
    * Get the planned end date of the project.
    *
    * @return the planned end date of the project.
    */
    public Calendar getEndPlannedDate() {
        return endPlannedDate;
    }

    /**
    * Get the actual end date of the project.
    *
    * @return the actual end date of the project.
    */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
    * Get the budget of the project.
    *
    * @return the budget of the project.
    */
    public double getBudget() {
        return budget;
    }

    /**
    * Get the client data of the project.
    *
    * @return the client data of the project.
    */
    public String[] getClientData() {
        return clientData;
    }

    /**
    * Get the collaborators of the project.
    *
    * @return the collaborators of the project.
    */
    public Employee[] getCollaborators() {
        return collaborators;
    }

    /**
    * Get the managers of the project.
    *
    * @return the managers of the project.
    */
    public Employee[] getManagers() {
        return managers;
    }

    //Stages
    /**
    * Get the names of all the stages in the project.
    *
    * @return the names of all the stages in the project.
    */
    public String[] getStageNames() {
        String[] stageNames = {stages[0].getName(), stages[1].getName(), stages[2].getName(), stages[3].getName(),
                               stages[4].getName(),stages[5].getName()};
        return stageNames;
    }

    /**
    * Get the name of the active stage in the project.
    *
    * @return the name of the active stage in the project.
    */
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
    /**
    * Get the types of capsules in the project.
    *
    * @return the types of capsules in the project.
    */
    public String[] getCapsuleTypes() {
        return stages[0].getCapsuleTypes();
    }

    /**
    * Get the information for all the capsules in the project, grouped by stage and capsule status.
    *
    * @param capsulesStatus the status of the capsules to get information for.
    * @return the information for all the capsules in the project, grouped by stage and capsule status.
    */
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

    /**
    * Get the total number of capsules in the project with the specified status.
    *
    * @param status the status of the capsules to get the total number for.
    * @return the total number of capsules in the project with the specified status.
    */
    public int getAmountCapsules(int status) { 
        int amountCapsules = 0; 
        for(int i = 0; i < stages.length; i++) {
            amountCapsules += stages[i].getAmountCapsules(status);
        }
        return amountCapsules;
    }

    /**
     * Returns an array with the amount of capsules per type in this project, taking into account all stages.
     * 
     * @return int[] - Array with the amount of capsules per type in this project
    */
    public int[] getAmountCapsulesPerType() {
        int[] amCapsPerType = new int[getCapsuleTypes().length];
        int[] amCapsPerTypeInStage = new int[getCapsuleTypes().length];

        for(int i = 0; i < stages.length; i ++) {
            amCapsPerTypeInStage = stages[i].getAmountCapsulesPerType();
            // to add
            for(int j = 0; j < amCapsPerType.length; j++) {
                amCapsPerType[j] += amCapsPerTypeInStage[j];
            }
        }

        return amCapsPerType; 
    }

    //Employee

    /**
    * Get the names of all the collaborators in the project.
    *
    * @return array with the names of all the collaborators
    */
    public String[] getCollaboratorNames() {
        String [] collaboratorNames = new String[collaborators.length];

        for(int i = 0; i < collaborators.length ; i++) {
            collaboratorNames[i] = collaborators[i].getName();
        }

        return collaboratorNames;
    }

    //--------- AUX
    /**
    * Closes the active stage and starts the next one, or ends the project if the active stage is the last one.
    *
    * @return true if the stage was closed successfully, false if there are no active stages
    */
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

    /**
    * Registers a new capsule in the active stage.
    *
    * @param collaboratorPos the position of the collaborator who is registering the capsule
    * @param description the description of the capsule
    * @param type the type of the capsule
    * @param lesson the lesson of the capsule
    * @return a message indicating whether the registration was successful or not
    */
    public String registerCapsule(int collaboratorPos, String description, int type, String lesson) {
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

    /**
    * Assigns hashtags to a given text.
    *
    * @param description the description of the capsule
    * @param lesson the lesson of the capsule
    * @return an ArrayList containing the hashtags found in the text
    */
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

    /**
    * Approves a capsule in the active stage.
    *
    * @param capsulePos the position of the capsule to be approved
    */
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

    /**
    * Makes a capsule public in the active stage.
    *
    * @param capsulePos the position of the capsule to be made public
    * @return a message indicating whether the operation was successful or not
    */
    public String publicCapsule(int capsulePos) {
        int count = 0;
        boolean isFound = false;
        String msg = "";
        
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
