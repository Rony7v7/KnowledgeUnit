package model;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class GreenSQA {
    private static final int SIZE = 11; //10 + 1 project that is pre-created to access its stage names in Main //MANEJAR LA ESCALABILIDAD
    private Project[] projects;

    private Employee[] managers;
    
    public GreenSQA() {
        this.projects = new Project[SIZE];

        this.managers = new Employee[] {new Employee("GERENTE 1", "MANAGER"),
                                        new Employee("GERENTE 2", "MANAGER"),
                                        new Employee("GERENTE 3", "MANAGER")};
        //Constructor
    }

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

    public String registerCapsule(int collaboratorPos, int projectPosition, String description, int type, String lesson) {
        return projects[projectPosition].registerCapsule(collaboratorPos, description, type, lesson);
    }

    public String approveCapsule(int projectPos, int capsulePos) {
        String msg = "\nEl proyecto ya fue finalizado.";
        //if the project choosed is active
        if(projectPos != -1) {

            //if the project has capsules under review
            if(capsulePos != -1 && getProjectStatus(projectPos)) {
                projects[projectPos].approveCapsule(capsulePos);
                msg = "\nCapsula Aprobada exitosamente.";
            } else {
                msg ="\nNo hay capsulas para revisar.";
            }
        }
        return msg;
    }

    public String publicCapsule(int projectPos, int capsulePos) {
        String msg;

        //if the project has capsules approved
        if(capsulePos != -1) {
            msg = "\nCapsula Publicada exitosamente.\n"+projects[projectPos].publicCapsule(capsulePos);
        } else {
            msg ="\nNo hay capsulas para publicar.";
        }

        return msg;
    }

    public void listCapsules() {

    }

    public void listlessons() {

    }

    public void showFullerProject() {
        
    }

    public void verifyCapsuleRegister() {

    }

    public void searchLesson() {

    }
    
    //Aux
    public int projectsIsEmpty() {
        int lastFullPosition = -1;
        boolean isEmpty = false;

        for(int i = 0; (i < SIZE && !isEmpty) ; i++) {
            if (projects[i] != null) {
                lastFullPosition = i;
            } else {
                isEmpty = true;
            }
        }
        return lastFullPosition;
    }

    public void registerCapsuleModel() {
        projects[10].registerCapsule(SIZE, null, SIZE, null);
    }
    //---------  Getters  ----------

    //Projects
    public int getProjectsSIZE() {
        return SIZE;
    }

    public String[] getProjectNames() {
        int lastedFullPosition = projectsIsEmpty();
        String[] projectNames = new String[lastedFullPosition+1];

        for(int i = 0; i <= lastedFullPosition ; i++) {
            projectNames[i] = projects[i].getName();
        }
        return projectNames;
    }

    public boolean getProjectStatus(int projectPosition) {
        return projects[projectPosition].getStatus();
    }

    public String getProjectEndDate(int projectPosition) {
        Calendar endDate = projects[projectPosition].getEndDate();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return format.format(endDate.getTime());
    }

    //Stages
    public String[] getStageNames() {
        projects[10] = new Project(null, null, null, 0.0, null, null);
        return projects[10].getStageNames();
    }

    public String getStageActiveName(int projectPosition) {
        return projects[projectPosition].getStageActiveName();
    }

    //Employees
    public String[] getManagerNames() {
        String[] managerNames = new String[managers.length];

        for(int i = 0; i < managers.length; i++) {
            managerNames[i] = managers[i].getName();
        }

        return managerNames;
    }

    public String[] getCollaboratorNames() {
        return projects[10].getCollaboratorNames();
    }

    //Capsules
    public String[] getCapsuleTypes() {
        return projects[10].getCapsuleTypes();
    }

    public String[][] getCapsulesInfo(int projectPosition,int capsulesStatus) {
        return projects[projectPosition].getCapsulesInfo(capsulesStatus);
    }
    
    public int getAmountCapsules(int projectPosition, int capsulesStatus) {
        return projects[projectPosition].getAmountCapsules(capsulesStatus);
    }
    //---------  Setters  ---------

}