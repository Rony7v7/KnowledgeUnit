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

    public void initProject() {
        int[] managersPosition;
        int amountManagers;
        String[] clientData = new String[2];

        String name;
        double budget;

        System.out.print("-------- CREACIÓN DE PROYECTO ---------\n\n"+
                         "Nombre del proyecto: ");
        name = input.nextLine();
            
        do{
            System.out.print("\n- Participantes del proyecto\n\nNumero de gerentes (3 max.) : ");

            //input Project Managers
            amountManagers = input.nextInt();
            input.nextLine();

        if(amountManagers < 1 || amountManagers > 3){
            System.out.println("\nIngresa una cantidad válida.");
        }

        }while(amountManagers < 1 || amountManagers > 3);
            
        managersPosition = new int[amountManagers];

        for(int i = 0; i < amountManagers; i++) {
            managersPosition[i] = inputEmployees("Gerente", 
                                                controller.getManagerNames(),
                                                controller.getManagerNames().length); //Managers Amount
        }

        //Input Project Client
        System.out.print("\nNombre del cliente: ");
        clientData[0] = input.nextLine();
        System.out.print("\nNúmero de telefono: ");
        clientData[1] = input.nextLine();
         
        System.out.print("\n- Presupuesto del proyecto: ");
        budget = input.nextDouble();
        input.nextLine();

            //Constructor
        controller.addProject(name, 0, budget, managersPosition, clientData);

        System.out.println("\n- Duración del proyecto\n");
            // Setted duration in months per stage of created project
        controller.setRecentProjectDurationAndDates(inputStagesDuration());  

        System.out.println("\nPROYECTO CREADO CON EXITO.");
            
        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
        
    }

    public void closeStage() {
        int projectPosToClose = 0;

        String msgValidation;

        int lastProjectPosition = controller.projectsIsEmpty();

        // 1. if there are projects
        if(lastProjectPosition != -1) {
            projectPosToClose = showActiveProjects(lastProjectPosition)-1;
            msgValidation = controller.closeStageProject(projectPosToClose);

            System.out.println(msgValidation);
        }

        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
    }

    public void registerCapsule() {

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

    public int showActiveProjects(int lastProjectPosition) {
        int option = 0;

        do{ 
            System.out.println("\nDe que proyecto desea culminar la etapa\n");

            for(int i = 0 ; i <= lastProjectPosition ; i++) {
                System.out.print((i+1)+". "+controller.getProjectNames()[i]);
                // if the project is active
                if(controller.getProjectStatus(i)) {
                    System.out.println(" |  Etapa activa: "+controller.getStageActive(i));
                } else{
                    System.out.println(" | PROYECTO CULMINADO | Fecha de culminación: "+controller.getProjectEndDate(i));
                }
            }
    
            System.out.print(">> ");
            option = input.nextInt();
            input.nextLine();
            
            //if    option is out of projects
            if(option < 1 || option > lastProjectPosition+1) {
                System.out.println("Opción incorrecta. ");
            }

            //while    option is out of projects
        }while(option < 1 || option > lastProjectPosition+1);
        
        return option;
    }

    public int inputEmployees(String employeeRole, String[] employeeNames, int amountEmployees) {
        int option = 0;

        do{
            System.out.println("\nSelecciona el "+employeeRole+" :\n");
            
            for(int i = 0; i < amountEmployees; i++) {
                System.out.println((i+1)+". "+employeeNames[i]);
            }
            System.out.print(">> ");
            option = input.nextInt();
            input.nextLine();

            if(option < 1 || option > amountEmployees){
                System.out.println("\nOpción inválida.");
            }

        }while(option < 1 || option > amountEmployees);

        return option-1;
    }

}