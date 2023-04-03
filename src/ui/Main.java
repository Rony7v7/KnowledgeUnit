package ui;

import java.util.Scanner;
import model.GreenSQA;

/**
 * @author  Rony
 */
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
            case 3: registerCapsule();
                break;
            case 4: approveCapsule();
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

    /**
     * initProject: This method 
     * @param projectName
     * @param budget
     * @param amounManagers
     * @param managersPosition
     * @param clientData[]
     * @param collaborator
     */
    public void initProject() {
        
        String name;
        double budget;
        int[] monthsPerStage;
        int amountManagers;
        int[] managersPosition;
        String[] clientData = new String[2];

        String msgValidation;

        System.out.print("-------- CREACIÓN DE PROYECTO ---------\n\n"+
                         "Nombre del proyecto: ");
        name = input.nextLine();
            
        do{
            System.out.print("\n- Participantes del proyecto\n\nNumero de gerentes (3 max.) : ");
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
        
        //Input Budget
        System.out.print("\n- Presupuesto del proyecto: ");
        budget = input.nextDouble();
        input.nextLine();

        //Input duration in months
        System.out.println("\n- Duración del proyecto\n");
        monthsPerStage = inputStagesDuration();

        msgValidation = controller.addProject(name, budget, monthsPerStage, managersPosition, clientData);

        System.out.println(msgValidation);

        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
        
    }

    public void closeStage() {
        int projectPosToClose = 0;
        String msgValidation;
        int lastProjectPosition = controller.projectsIsEmpty();

        System.out.println("-------- CULMINACION DE ETAPAS ---------");

        // 1. if there are projects
        if(lastProjectPosition != -1) {
            projectPosToClose = showProjects(lastProjectPosition)-1;
            msgValidation = controller.closeStageProject(projectPosToClose);
        } else {
            msgValidation = "\nNo hay proyectos registrados.";
        }

        System.out.println(msgValidation);

        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
    }

    public void registerCapsule() { 
        String msgValidation ="\nNo hay proyectos registrados.";
        int lastProjectPosition = controller.projectsIsEmpty();
        int projectPosToRegister;

        int collaboratorPos;
        String description;
        String[] capsuleTypes;
        int capsuleType;
        String lesson;
        
        System.out.println("-------- REGISTRO DE CAPSULAS ---------");

        if(lastProjectPosition != -1) {
            projectPosToRegister = showProjects(lastProjectPosition)-1;

            //if the project choosed is active
            if(projectPosToRegister >= 0) {
                collaboratorPos = inputEmployees("COLABORADOR",
                controller.getCollaboratorNames(),
                controller.getCollaboratorNames().length);

                capsuleTypes = controller.getCapsuleTypes();

                do{
                    System.out.println("\nTipo de capsula: ");

                    for(int i = 0 ; i < capsuleTypes.length ; i++ ) {
                    System.out.println((i+1)+". "+capsuleTypes[i]);
                    }

                    System.out.print("\n>> ");
                    capsuleType = input.nextInt();
                    input.nextLine();

                    if(capsuleType < 1 || capsuleType > capsuleTypes.length) {
                    cleanScreen();
                    System.out.println("\nOpción incorrecta");
                    }

                }while(capsuleType < 1 || capsuleType > capsuleTypes.length);

                System.out.println("\nLa capsula debe tener mínimo una palabra clave (Ejemplo #Pruebas Funcionales#)");
                System.out.print("Contexto de la situación a registrar: ");
                description = input.nextLine();

                System.out.print("\nLección aprendida\n- ");
                lesson = input.nextLine();

                msgValidation = controller.registerCapsule(collaboratorPos, projectPosToRegister,description, capsuleType-1, lesson);
            } else {
                msgValidation = "\nEl proyecto ya fue finalizado";
            }
        }

        System.out.println(msgValidation);

        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
    }

    public void approveCapsule() { 
        int projectPos = 0;
        int capsulePos = 0;
        String msgValidation = "\nNo hay proyectos registrados.";
        int lastProjectPosition = controller.projectsIsEmpty();

        System.out.println("-------- APROBACIÓN DE CAPSULAS ---------");

        if(lastProjectPosition >= 0) {
            projectPos = showProjects(lastProjectPosition)-1;

            capsulePos = showCapsules(projectPos,1)-1;

            msgValidation = controller.approveCapsule(projectPos,capsulePos);
        }

        System.out.println(msgValidation);
        
        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
    }
    
    public void publicCapsule() {
        int projectPos = 0;
        int capsulePos = 0;
        String msgValidation = "\nNo hay proyectos registrados";
        int lastProjectPosition = controller.projectsIsEmpty();

        System.out.println("-------- PUBLICACIÓN DE CAPSULAS ---------");

        if(lastProjectPosition != -1) {
            projectPos = showProjects(lastProjectPosition);
            //if its a ended project, adapt 
            projectPos = (projectPos>=0)? projectPos-1:-projectPos-1;

            capsulePos = showCapsules(projectPos, capsulePos)-1;

            msgValidation = controller.publicCapsule(projectPos, capsulePos);
        }

        System.out.println(msgValidation);

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
        int[] MonthsPerStage = new int[stageNames.length];

        for(int i = 0; i < stageNames.length; i++) {
            System.out.print("Ingresa la duración en meses de la etapa "+stageNames[i]+" : ");
            MonthsPerStage[i] = input.nextInt();
            input.nextLine();
        }
        return MonthsPerStage;
    }

    public int showProjects(int lastProjectPosition) {
        int option = 0;

        do{ 
            System.out.println("\nEn que proyecto desea ejecutar la acción\n");

            for(int i = 0 ; i <= lastProjectPosition ; i++) {
                System.out.print((i+1)+". "+controller.getProjectNames()[i]);
                // if the project is active
                if(controller.getProjectStatus(i)) {
                    System.out.println(" |  Etapa activa: "+controller.getStageActiveName(i));
                } else{
                    System.out.println(" | PROYECTO CULMINADO | Fecha de culminación: "+controller.getProjectEndDate(i));
                }
            }
    
            System.out.print(">> ");
            option = input.nextInt();
            input.nextLine();
            
            //if    option is out of projects
            if(option < 1 || option > lastProjectPosition+1) {
                cleanScreen();
                System.out.println("\nOpción incorrecta. ");
            }

            //while    option is out of projects
        }while(option < 1 || option > lastProjectPosition+1);

        // if option is a ended project, option switch to negative form to filter.
        if(!controller.getProjectStatus(option-1)) {
            option = -option;
        }
        
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

    public int showCapsules(int projectPosition, int capsulesStatus) { //0 -> all ; 1 -> Under review ; 2 -> Approved ; 3 -> published
        int option = 0;
        int count = 0;

        //If the project is active
        if(projectPosition >= 0) {
            //if the project has capsules under review
            if(controller.getAmountCapsules(projectPosition, capsulesStatus) > 0) {
                String[][] capsulesInfo = controller.getCapsulesInfo(projectPosition, capsulesStatus);

                do{
                    System.out.println("\nSelecciona la capsula a aprobar");

                    //for each stage
                    for(int i = 0; i < capsulesInfo.length; i++) {
                        //print the capsules info
                        if(capsulesInfo[i] != null) {
                            for(int j = 0; j < capsulesInfo[i].length; j++) {
                                count++;
                                System.out.println("\n"+count+". "+capsulesInfo[i][j]);
                            }
                        }
                        
                    }

                    System.out.print("\n>> ");
                    option = input.nextInt();
                    input.nextLine();

                    if(option < 1 || option > capsulesInfo.length) {
                        System.out.println("\nOpción incorrecta.");
                    }

                }while(option < 1 || option > capsulesInfo.length);
            }
        }

        return option;
    }


}