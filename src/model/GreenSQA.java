package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * GreenSQA class
 */
public class GreenSQA {
    private static final int SIZE = 11; //10 + 1 project that is pre-created to access its stage names in Main
    private Project[] projects;

    private Employee[] managers;
    
/**
 * Constructor for GreenSQA class. Initializes the projects array to a new Project
 * array of size SIZE and initializes the managers array to an array of three
 * Employee objects with hardcoded values.
*/
    public GreenSQA() {
        this.projects = new Project[SIZE];

        this.managers = new Employee[] {new Employee("GERENTE 1", "MANAGER","12345678"),
                                        new Employee("GERENTE 2", "MANAGER","12345678"),
                                        new Employee("GERENTE 3", "MANAGER","12345678")};
    }

    /**
     * addProject: This method adds the project with the parameters entered to the project array.
     * It assigns the dates corresponding to each stage and assigns the project in the first valid position,
     * if there are no valid positions, it returns an error message. 
     * @param name The project's name
     * @param budget The budget's project
     * @param monthsPerStage Duration in months of each stage
     * @param managersPosition Positions of the project managers
     * @param clientData Name and phone number of the client
     * @return Validation message
     */
    public String addProject(String name, double budget,int[] monthsPerStage ,int[] managersPosition, String[] clientData) { 
        String msg = "\nCAPACIDAD MAXIMA DE PROYECTOS.";

        int totalMonthsProject = 0;
        int projectPos = projectsIsEmpty()+1;

        Calendar projectPlannedEndDate = Calendar.getInstance();
        Calendar stagePlannedStartDate = Calendar.getInstance();
        Calendar stagePlannedEndDate = Calendar.getInstance();

        //Create to model project to access the project stages in Main class

        if(projectPos < projects.length-1) {

            projects[projectPos] = new Project(name, stagePlannedStartDate, stagePlannedEndDate, budget, managers, clientData);

            //Set duration in months per Stage and planned start - end dates
            for(int i = 0; i < monthsPerStage.length ; i++) {

                totalMonthsProject += monthsPerStage[i];

                //Set Start planned date
                stagePlannedStartDate.add(Calendar.MONTH, totalMonthsProject);
                projects[projectPos].setStartPlannedDate(stagePlannedEndDate);

                //Set End planned date
                stagePlannedEndDate.add(Calendar.MONTH, totalMonthsProject);
                projects[projectPos].setEndPlannedDate(stagePlannedEndDate);
                
                //Set months
                projects[projectPos].setMonthsPerStage(i, monthsPerStage[i]); 
            }
            
            //Set end planned date of project 
            projectPlannedEndDate.add(Calendar.MONTH, totalMonthsProject);
            projects[projectPos].setEndPlannedDate(projectPlannedEndDate);

            msg = "\nPROYECTO CREADO CON EXITO.";
        } 

        return msg;        
    }

    /**
     * This method close de active stage of a project, and active the next stage,
     * if there are not more stages, it inactive the project
     * @param projectPosition porject position to close the active stage
     * @return Validation message
     */
    public String closeStageProject(int projectPosition) {
        String msg = "No hay proyectos activos";

        //if the project is active
        if(projectPosition >= 0) {

            if(projects[projectPosition].closeStage()) {
                msg =  "\nEtapa culminada con éxito, nueva etapa: "+getStageActiveName(projectPosition);
            }else{
                msg = "\nUltima etapa culminada con éxito, proyecto finalizado.";
            }

        }else {
            msg = "\nProyecto ya culminado anteriormente.";
        }
        
        return msg;
    }

    /**
     * This method receives the parameters to register a capsule and sends it to the Project class via dependency injection 
     * @param collaboratorPos Position of the creator / collaborator of the capsule
     * @param projectPosition Position of the project where to register the capsule
     * @param description Description of the capsule with a minimum keyword
     * @param type  Type of the capsule
     * @param lesson Lesson of the capsule with a minimum keyword
     * @return Validation message
     */
    public String registerCapsule(int collaboratorPos, int projectPosition, String description, int type, String lesson) {
        return projects[projectPosition].registerCapsule(collaboratorPos, description, type, lesson);
    }

    /**
     * Approves a capsule for a project at the specified position.
     * @param projectPos the position of the project in the projects array
     * @param capsulePos the position of the capsule in the stage active's capsule array
     * @return Validation message
     */
    public String approveCapsule(int projectPos, int capsulePos) {
        String msg = "\nEl proyecto ya fue finalizado.";
        //if the project choosed is active and has capsules under review
        if(projectPos >= 0 && capsulePos >= 0) {
            projects[projectPos].approveCapsule(capsulePos);
            msg = "\nCapsula Aprobada exitosamente.";
        } else if(projectPos >= 0) {
            msg ="\nNo hay cápsulas para revisar.";
        }

        return msg;
    }

    /**
     * Publishes a capsule for a project at the specified position.
     * @param projectPos the position of the project in the projects array
     * @param capsulePos the position of the capsule in the project's capsule array
     * @return Validation message
     */
    public String publicCapsule(int projectPos, int capsulePos) {
        String msg;

        //if the project has capsules approved
        if(capsulePos >= 0) {
            msg = "\nCapsula Publicada exitosamente.\n"+projects[projectPos].publicCapsule(capsulePos);
        } else {
            msg ="\nNo hay cápsulas para publicar.";
        }

        return msg;
    }

    /**
     * Returns an array with the amount of capsules per type in all projects.
     * 
     * @return an array with the amount of capsules per type in all projects
    */
    public int[] listCapsulesAmount() {
        int[] capsulesAmount = new int[getCapsuleTypes().length];
        int[] capsulesAmountInProject = new int [getCapsuleTypes().length];
        int projectsAmount = projectsIsEmpty()+1;
        
        for(int i = 0; i < projectsAmount; i++) {
            capsulesAmountInProject = projects[i].getAmountCapsulesPerType();
            //to add
            for(int j = 0; j < capsulesAmountInProject.length; j++){
                capsulesAmount[j] += capsulesAmountInProject[j];
            }
        }

        return capsulesAmount;
    }

    /** 
     * Returns the fuller project name
     * 
     * @return Fuller project name
     */
    public String showFullerProject() {
        int capsulesAmount = -1;
        String projectName ="";
        int capsulesAmountInProject;

        for(int i = 0; i < SIZE-1; i++) {
            capsulesAmountInProject = (projects[i] == null) ? 0 : getAmountCapsules(i, 0);

            if(capsulesAmountInProject > capsulesAmount) {
                capsulesAmount = capsulesAmountInProject;
                projectName = getProjectNames()[i];
            }
        }
        
        return projectName;
    }

    /**
     * Returns the confirmation if the collaborator has capsules
     * 
     * @param name name to search
     * @return msg  validation
     */
    public String verifyCapsuleRegister(String name) {
        String msg = "\nNombre no encontrado.";
        int collabPos = getCollabPosByName(name);
        int projectsEmpty = 0;
        ArrayList<String> collabCapsulesIds;
    
        if( collabPos != -1) {
            msg = "\nEl colaborador ha registrado las siguientes cápsulas: ";

            for(int i = 0; i < projectsIsEmpty()+1; i++) {
                collabCapsulesIds = projects[i].getCollaborators()[collabPos].getCollabCapsulesIds();

                if(!collabCapsulesIds.isEmpty()) {
                    msg += collabCapsulesIds;
                } else {
                    projectsEmpty++;
                }
            }

            if(projectsEmpty == projectsIsEmpty()+1) {
                msg = "\nEl colaborador no ha registrados cápsulas en ningun proyecto.";
            }

        }

        return msg;
    }

    /**
     * returns a string with info founded by a keyword search 
     * @param keyword
     * @return
     */
    public String searchCapsulesByKeyword(String keyword) {
        String msg = "";

        for(int i = 0; i <= projectsIsEmpty(); i++ ) {
            msg += "\n"+projects[i].searchCapsuleByHashtag(keyword);
        }

        return msg.isBlank()?"\nNo se encontraron cápsulas con las palabras clave ingresadas.":
                             "\nResultado de la busqueda: "+msg;
    }
    
    //Aux
    /**
     * This method return the last full position in projects array
     * @return lastFullPosition
     */
    public int projectsIsEmpty() {
        int lastFullPosition = -1;
        boolean isEmpty = false;

        for(int i = 0; (i < SIZE-1 && !isEmpty) ; i++) {
            if (projects[i] != null) {
                lastFullPosition = i;
            } else {
                isEmpty = true;
            }
        }
        return lastFullPosition;
    }

    /**
     * This method register the capsule model instance, to get its info.
     */
    public void registerCapsuleModel() {
        projects[10].registerCapsule(SIZE, null, SIZE, null);
    }
    
    //---------  Getters  ----------

    //Projects
    /**
    * Returns the projects size
    * @return Size of the projects array
    */
    public int getProjectsSIZE() {
        return SIZE;
    }

    /**
     * Returns an array of project names.
     * @return An array of strings with the names of all registered projects.
     */
    public String[] getProjectNames() {
        int lastedFullPosition = projectsIsEmpty();
        String[] projectNames = new String[lastedFullPosition+1];

        for(int i = 0; i <= lastedFullPosition ; i++) {
            projectNames[i] = projects[i].getName();
        }
        return projectNames;
    }

    /**
    * Returns the status of a given project.
    * @param projectPosition The position of the project in the projects array.
    * @return True if the project is active, false if it is not.
    */
    public boolean getProjectStatus(int projectPosition) {
        return projects[projectPosition].getStatus();
    }

    /**
    * Returns the end date of a given project formatted as a string.
    * @param projectPosition The position of the project in the projects array.
    * @return A string with the end date of the project formatted as "dd/MM/yyyy".
    */
    public String getProjectEndDate(int projectPosition) {
        Calendar endDate = projects[projectPosition].getEndDate();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return format.format(endDate.getTime());
    }

    //Stages
    /**
    * Returns an array of stage names for the default project.
    * @return An array of strings with the names of all stages in the default project.
    */
    public String[] getStageNames() {
        projects[10] = new Project(null, null, null, 0.0, null, null);
        return projects[10].getStageNames();
    }

    /**
    * Returns the name of the active stage for a given project.
    * @param projectPosition The position of the project in the projects array.
    * @return A string with the name of the active stage for the project.
    */
    public String getStageActiveName(int projectPosition) {
        return projects[projectPosition].getStageActiveName();
    }

    //Employees
    /**
    * Returns an array of manager names.
    * @return An array of strings with the names of all registered managers.
    */
    public String[] getManagerNames() {
        String[] managerNames = new String[managers.length];

        for(int i = 0; i < managers.length; i++) {
            managerNames[i] = managers[i].getName();
        }

        return managerNames;
    }

    /**
    * Returns an array of collaborator names for the default project.
    * @return An array of strings with the names of all collaborators in the default project.
    */
    public String[] getCollaboratorNames() {
        return projects[10].getCollaboratorNames();
    }
    
    /**
     * Returns the position of the collaborator whit the name typed. 
     * 
     * @param name
     * @return Collaborator's position
     */
    public int getCollabPosByName(String name) {
        int pos = -1;
        boolean isFound = false;
        Employee[] collaborators = projects[10].getCollaborators();

        for(int i = 0; i < collaborators.length & !isFound; i++) {
            if(collaborators[i].getName().equals(name)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    //Capsules
    /**
    * Returns an array of capsule types for the default project.
    * @return An array of strings with the types of all capsules in the default project.
    */
    public String[] getCapsuleTypes() {
        return projects[10].getCapsuleTypes();
    }

    /**
    * Returns a two-dimensional array with the information of all capsules in a given project and status.
    * @param projectPosition The position of the project in the projects array.
    * @param capsulesStatus The status of the capsules to retrieve (1 for pending, 2 for approved, 3 for published).
    * @return A two-dimensional array with the information of all capsules in the specified project and status.
    */
    public String[][] getCapsulesInfo(int projectPosition,int capsulesStatus) {
        return projects[projectPosition].getCapsulesInfo(capsulesStatus);
    }
    
    /**
    * Returns the amount of capsules with a given status for a given project.
    * @param projectPosition The position of the project in the projects array.
    * @param capsulesStatus The status of the capsules to count (1 for pending, 2 for approved, 3 for published).
    * @return The amount of capsules with the specified status in the specified project.
    */
    public int getAmountCapsules(int projectPosition, int capsulesStatus) {
        return projects[projectPosition].getAmountCapsules(capsulesStatus);
    }

}