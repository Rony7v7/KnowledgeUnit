package model;

public class Employee {
    private String name;
    private String role;
    private String phoneManager; 

    private Capsule[] collabCapsules = new Capsule[50]; //ARRAYLIST
    private int amountCapsules = 0;
    private String[] capsuleTypes;


    public Employee(String name, String role) { //SET phone manager
        this.name = name;
        this.role = role;

        this.collabCapsules = new Capsule[50];
        this.capsuleTypes = new String[] {"TÉCNICO","GESTIÓN","DOMINIO","EXPERIENCIAS"};
    }


    //------- Getters -------
    public String getPhoneManager() {
        return phoneManager;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getAmountCapsules() {
        return amountCapsules;
    }

    public String[] getCapsuleTypes() { //es importante?
        return capsuleTypes;
    }

    //-------- Setters -------
    public void setPhoneManager(String phoneManager) {
        this.phoneManager = phoneManager;
    }

    public void addCapsule(Capsule capsule) {
        if(amountCapsules < 50) {
            collabCapsules[amountCapsules] = capsule;
            amountCapsules ++;
        }
    }


}
