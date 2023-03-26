package ui;

import java.util.Scanner;
import model.GreenSQA;

class Main{

    private Scanner input;
    private GreenSQA controller;

    public Main() {
        controller = new GreenSQA();
        input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main view = new Main();
        int option;

        do{
            option = view.menu();
            view.executeOption(option);
        }while( option != 11 );

    }

    public int menu() {
        int option = 0;

        do{
            cleanScreen();
            System.out.print("--------- BIENVENIDO --------\n"+
                             "       ELige una opción\n\n"+
                             "- Gestion y capsulas\n\n"+
                             "1. Crear proyecto\n"+
                             "2. Culminar etapa de un proyecto\n"+
                             "3. Registrar cápsula\n"+
                             "4. Aprobar cápsula\n"+
                             "5. Publicar cápsula\n\n"+
                             "- Informes y estadisticas\n\n"+
                             "6. Listar cápsulas\n"+
                             "7. Listar lecciones aprendidas\n"+
                             "8. Proyecto con más capsulas registradas\n"+
                             "9. Verificar registro de capsulas\n"+
                             "10. Busqueda de lecciones\n\n"+
                             "11. Exit\n\n"+
                             ">> ");
            option = input.nextInt();
            input.nextLine();
            cleanScreen();

            if(option < 1 || option > 11){
                System.out.println("OPCIÓN INVALIDA*\n");
                System.out.print("\nENTER PARA CONTINUAR.");
                input.nextLine();
            }

        }while(option < 1 || option > 11);

        return option;
    }

    public void executeOption(int option) {
        switch (option) {
            case 1: initProject();
                break;
            case 2: closeStage();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                break;
        }
    }

    public void initProject() { // no lee nombres de proyectos separados por espacios
        if(controller.projectsIsEmpty() != controller.getProjectsSIZE()-1) { //Max position into projects array
            //Manager[] projectManagers;
            //Client client;
            controller.addProject(null, 0, 0, null, null);

            System.out.print("-------- CREACIÓN DE PROYECTO ---------\n\n"+
                             "Nombre del proyecto: ");
            controller.setRecentProjectName(input.next()); 
            
            System.out.println("\nDuración del proyecto\n");
            controller.setRecentProjectDurationAndDates(inputStagesDuration());

            System.out.print("\nPresupuesto del proyecto: ");
            controller.setRecentProjectBudget(input.nextDouble());
            input.nextLine();

            System.out.println("\nPROYECTO CREADO EXITOSAMENTE!");
            
        } else {
            System.out.println("\nCAPACIDAD MAXIMA DE PROYECTOS");
        }
        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
        
    }

    public void closeStage() {
        int projectPos = listProjectsToChoose()-1;
        boolean validation = (projectPos == 0) ? controller.closeStageProject(projectPos):false;
        
        if(validation) {
            System.out.println("Etapa culminada con éxito, nueva etapa: "+controller.getStageActive(projectPos));
        } else if(projectPos != -1){
            System.out.println("Ultima etapa culminada con éxito, proyecto finalizado.");
        } else{
            System.out.println("No hay proyectos activos.");
        }

        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
    }

    //-------------Aux Methods -----------

    public void cleanScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public int[] inputStagesDuration( ) {
        String[] stageNames = controller.getStageNames();
        int[] MonthsPerStage = new int[6];

        for(int i = 0; i < 6; i++) {
            System.out.print("Ingresa la duración en meses de la etapa "+stageNames[i]+" : ");
            MonthsPerStage[i] = input.nextInt();
            input.nextLine();
        }
        return MonthsPerStage;
    }

    public int listProjectsToChoose() { //VALIDAR
        int option = 0;
        int lastProjectPosition = controller.projectsIsEmpty();

        //if there are projects
        if(lastProjectPosition != -1) {
            //if 
            if(lastProjectPosition == controller.countInactiveProjects()-1){
                System.out.println("De que proyecto desea culminar la etapa\n");

                for(int i = 0; i <= lastProjectPosition;i++) {
                    // if there are actives projects
                    if(controller.getProjectStatus(i)) {
                        System.out.println((i+1)+". "+controller.getProjectNames()[i]+" |  Etapa activa: "+controller.getStageActive(i));
                    } else{
                        option ++; //skip option to choose the position correct into array
                    }
                }
                
                System.out.print(">> ");
                option = input.nextInt();
                input.nextLine();
            }
        }

        return option; 
    }
}