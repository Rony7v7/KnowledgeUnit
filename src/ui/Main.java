package ui;

import java.util.Scanner;
import model.GreenSQA;

/**
 * Knowledge System
 * @author  Rony Ordoñez
 */
public class Main{

    private Scanner input;
    private GreenSQA controller;

    /**
     * Constructor of Main
     */
    public Main() {
        controller = new GreenSQA();
        input = new Scanner(System.in);
    }

    /**
     * Main method
     * @param args An array of String objects representing the command-line arguments.
     */
    public static void main(String[] args) {
        Main view = new Main();
        int option;

        do{
            option = view.menu();
            view.executeOption(option);
        }while( option != 11 );

    }

    /**
     * This method show all the requierement options
     * @return user's option
     */
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

    /**
     * This method execute the user's option in the menu
     * @param option user's option
     */
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
            case 5: publicCapsule();
                break;
            case 6: showCapsulesAmount();
                break;
            case 7: showLessonsPerStage();
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
     * initProject: this method is in charge of asking the user for the data of a project in order to register it and create the instance.
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

    /**
     * This method closes a stage in a project by prompting the user to select the project and confirming the closure. 
     * If there are no projects registered in the system, an error message is displayed. 
     * 
     * The method retrieves the last project position from the controller, and then displays a list of projects 
     * for the user to choose from. Once a project is selected, the method calls the controller's 
     * "closeStageProject" method to confirm the closure of the stage. 
     */
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

    /**
    * Registers a new capsule for a selected project and collaborator. Prompts the user for input to create the new capsule.
    * Displays an error message if there are no projects registered or if the selected project has already been finalized.
     */
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

    /**
     * This method allows to approve a capsule.
     * It shows the list of projects and capsules that are available for approval.
     * Once a project and a capsule are selected, the method calls the controller to approve the capsule.
     */
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
    
    /**
     * This method allows to publish a registered capsule in a project. It shows a list of existing projects
     * and a list of capsules registered in the selected project, and then calls the corresponding method
     * in the controller to publish the selected capsule.
     */
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

            capsulePos = showCapsules(projectPos, 2)-1;

            msgValidation = controller.publicCapsule(projectPos, capsulePos);
        }

        System.out.println(msgValidation);

        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
    }

    /**
     * This method show the capsules amount per type in all projects
     */
    public void showCapsulesAmount() {
        String[] capsuleTypes;
        int[] capsulesAmount;
        int lastProjectPosition = controller.projectsIsEmpty();

        System.out.println("---------- CANTIDAD DE CÁPSULAS ---------");

        if(lastProjectPosition != -1) {
            capsuleTypes = controller.getCapsuleTypes();
            capsulesAmount = controller.listCapsulesAmount();
            
            for(int i = 0; i < capsuleTypes.length; i++) {
                System.out.println("\n- "+capsuleTypes[i]+" --> "+capsulesAmount[i]+"");
            }
        } else{
            System.out.println("\nNo hay proyectos registrados.");
        }

        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();

    }

    /**
     * This method show all lessons of the capsules in a specific stage.
     */
    public void showLessonsPerStage() {
        String[] stageNames;
        String[] capsulesInStage;
        int option;

        System.out.println("--------- LECCIONES APRENDIDAS POR ETAPA -------");

        if(controller.projectsIsEmpty() != -1) {
            stageNames = controller.getStageNames();

            System.out.println("\nElige la etapa:\n");

            //choose stage
            do{
                for(int i = 0 ; i < stageNames.length; i++) {
                    System.out.println(+(i+1)+". "+stageNames[i]);
                }
                System.out.print("\n>> ");
                option = input.nextInt();
                input.nextLine();
                System.out.println("");
                
                if(option < 1 || option > stageNames.length) {
                    System.out.println("\nOpción incorrecta.");
                }

            }while(option < 1 || option > stageNames.length);

            //show capsules info
            for(int i = 0; i <= controller.projectsIsEmpty(); i++) {
                capsulesInStage = controller.getCapsulesInfo(i, 0)[option-1];

                if(capsulesInStage != null) {
                    for(int j = 0; j < capsulesInStage.length; j++) {
                        System.out.println("\n"+capsulesInStage[j]);
                     }
                } else {
                    System.out.println("No hay etapas registradas en el proyecto "+controller.getProjectNames()[i]);
                }
            }
            
        }else {
            System.out.println("\nNo hay proyectos registrados.");
        }

        System.out.print("\nENTER PARA CONTINUAR.");
        input.nextLine();
    }
    
    //-------------Aux Methods -----------

    /**
     * Clean the screen
     */
    public void cleanScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    /**
     * This method asks the user for the duration in months of each stage of the project
     * @return array whit duration in months per stage
     */
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

    /**
     * Show all the projects to choose
     * @param lastProjectPosition last full position of a project, also is the projects size -1
     * @return User's option
     */
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

    /**
     * Show all the employees, managers or collaborators to choose from and get user's input.
     *
     * @param employeeRole The role of the employees to be displayed.
     * @param employeeNames An array containing the names of the employees to be displayed.
     * @param amountEmployees The number of employees to be displayed.
     * @return The index of the selected employee.
     */
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

    /**
     * Show all capsules under review, approved or published for a given project to choose from and get user's input.
     *
     * @param projectPosition The position of the project in the list of active projects.
     * @param capsulesStatus An integer representing the status of the capsules to be displayed.
     *                      0 for "Under Review", 1 for "Approved", 2 for "Published".
     * @return The index of the selected capsule.
     */
    public int showCapsules(int projectPosition, int capsulesStatus) {
        int option = 0;
        int count = 0;

        //If the project is active
        if(projectPosition >= 0) {
            //if the project has capsules
            if(controller.getAmountCapsules(projectPosition, capsulesStatus) > 0) {
                String[][] capsulesInfo = controller.getCapsulesInfo(projectPosition, capsulesStatus);

                do{
                    System.out.println("\nSelecciona la capsula:");

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

                    if(option < 1 || option > count) {
                        System.out.println("\nOpción incorrecta.");
                    }

                }while(option < 1 || option > count);
            }
        }

        return option;
    }

}