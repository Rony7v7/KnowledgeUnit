package model;
import java.util.Calendar; //??? SE DEBE IMPORTAR OTRA VEZ? O DONDE SE IMPORTA POR SOLO UNA VEZ

public class GreenSQA {
    private static final int SIZE = 10;
    private Project[] projects = new Project[SIZE];

    public GreenSQA() {
        //Constructor
    }

    public void addProject(String name, int durationInMonths, double budget, Manager[] managers, Client client) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, durationInMonths);

        for(int i = 0; i < SIZE ; i++) {
            if ( projects[i] == null) {
                projects[i] = new Project(name, startDate, endDate, budget, managers, client);
                i = SIZE;
            }
        }
    }

    public void endProject() {

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
    
    //Auxiliares
    public int projectsIsEmpty() { //NO SE SI SEA NECESARIA TOTALMENTE
        int fullPositions = 0;
        boolean isEmpty = false;
        for(int i = 0; (i < SIZE && !isEmpty) ; i++) {
            if (projects[i] != null) {
                fullPositions = i;
            } else {
                isEmpty = true;
            }
        }
        return fullPositions;
    }


    //Getters
    public int getSIZE() {
        return SIZE;
    }

    public String[] getStageNames() {
        return projects[0].getStageNames();
    }


    //Setters
    public void setRecentProjectName(String name) {
        projects[(projectsIsEmpty())].setName(name);
    }

    public void setRecentProjectDurationInMonths(int[] MonthsPerStage) {
        int totalMonthsProject = 0;

        for(int i = 0; i < MonthsPerStage.length ; i++) {
            totalMonthsProject += MonthsPerStage[i];
            projects[projectsIsEmpty()].setMonthsPerStage(i, MonthsPerStage[i]);

        }
    }

    public void setRecentProjectBudget(double budget) {
       projects[projectsIsEmpty()].setBudget(budget); 
    }

}