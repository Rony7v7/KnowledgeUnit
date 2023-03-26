package model;

import java.util.Calendar; //??? SE DEBE IMPORTAR OTRA VEZ? O DONDE SE IMPORTA POR SOLO UNA VEZ

public class GreenSQA {
    private static final int SIZE = 10;
    private Project[] projects = new Project[SIZE];
    private Employee[] managers = {new Employee("GERENTE 1", "MANAGER"),
                                   new Employee("GERENTE 2", "MANAGER"),
                                   new Employee("GERENTE 3", "MANAGER")};
    
    public GreenSQA() {
        //Constructor
    }

    public void addProject(String name, int durationInMonths, double budget, int[] managersPosition, String[] clientData) { 
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, durationInMonths);

        //Assigned managers choosed to managers into the proyect
        Employee[] managersPerProject = new Employee[managersPosition.length];

        for(int i = 0; i < managersPosition.length ; i++) {
             managersPerProject[i] = managers[managersPosition[i]];
        }

        //Add project to the first valid position
        for(int i = 0; i < SIZE ; i++) {
            if ( projects[i] == null) {
                projects[i] = new Project(name, startDate, endDate, budget, managersPerProject, clientData);
                i = SIZE;
            }
        }
    }

    public boolean closeStageProject(int projectPosition) {
        return projects[projectPosition].closeStage();
    }

    public void registerCapsule() {
        
    }

    public void approveCapsule() {
        
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
    public int projectsIsEmpty() { //NO SE SI SEA NECESARIA TOTALMENTE
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

    public int countInactiveProjects() {
        int amountInactiveProjects = 0;
        int amountProjects = projectsIsEmpty()+1;

        for(int i = 0; i < amountProjects; i++) {
            if(!projects[i].getStatus()) {
                amountInactiveProjects++;
            }
        }

        return amountInactiveProjects;
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

    //Stages
    public String[] getStageNames() {
        return projects[0].getStageNames();
    }

    public String getStageActive(int projectPosition) {
        return projects[projectPosition].getStageActive();
    }

    //Employees
    public String[] getManagerNames() {
        String[] managerNames = new String[managers.length];

        for(int i = 0; i < managers.length; i++) {
            managerNames[i] = managers[i].getName();
        }

        return managerNames;
    }

    //---------  Setters  ---------
    // public void setRecentProjectName(String name) {
    //     projects[(projectsIsEmpty())].setName(name);
    // }

    public void setRecentProjectDurationAndDates(int[] monthsPerStage) {
        int totalMonthsProject = 0;
        int projectPos = projectsIsEmpty();
        Calendar projectPlannedEndDate = Calendar.getInstance();
        Calendar stagePlannedStartDate = Calendar.getInstance();
        Calendar stagePlannedEndDate = Calendar.getInstance();

        //Set duration in months per Stage and planned start - end dates
        for(int i = 0; i < monthsPerStage.length ; i++) {

            //Set Start planned date
            stagePlannedStartDate.add(Calendar.MONTH, totalMonthsProject);
            projects[projectPos].setStartPlannedDate(stagePlannedEndDate);

            totalMonthsProject += monthsPerStage[i];

            //Set End planned date
            stagePlannedEndDate.add(Calendar.MONTH, totalMonthsProject);
            projects[projectPos].setEndPlannedDate(stagePlannedEndDate);
            
            //Set months
            projects[projectPos].setMonthsPerStage(i, monthsPerStage[i]); 

        }
        
        //Set end planned date of project 
        projectPlannedEndDate.add(Calendar.MONTH, totalMonthsProject);
        projects[projectPos].setEndPlannedDate(projectPlannedEndDate);


    }

    // public void setRecentProjectBudget(double budget) {
    //    projects[projectsIsEmpty()].setBudget(budget); 
    // }

}