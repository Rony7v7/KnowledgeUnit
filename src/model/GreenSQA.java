package model;
import java.util.Calendar; //??? SE DEBE IMPORTAR OTRA VEZ? O DONDE SE IMPORTA POR SOLO UNA VEZ

public class GreenSQA {
    private static final int SIZE = 10;
    private Project[] projects = new Project[SIZE];

    public GreenSQA() {
        //Constructor
    }

    public void createProjec(String name, int durationInMonths, Double budget, Manager[] managers, Client client) {
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

    public Calendar toCalendar(int[] dateString) {
        Calendar date = Calendar.getInstance();
        date.set(dateString[2], dateString[1], dateString[0]); 
        return date;
    }

}