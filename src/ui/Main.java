package ui;

import java.util.Scanner;

import model.GreenSQA;
import model.Project;

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
            System.out.print("\n--------- BIENVENIDO --------\n"+
                               "      ELige una opción        \n\n"+
                               "- Etapas y capsulas\n\n"+
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
                               " >> ");
            option = input.nextInt();
            input.nextLine();
            System.out.println("");

            if(option < 1 || option > 11){
                System.out.println("OPCIÓN INVALIDA*\n");
            }

        }while(option < 1 || option > 11);

        return option;
    }

    public void executeOption(int option) {
        switch (option) {
            case 1:
                break;
            case 2:
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

}