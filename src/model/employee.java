package model;

public class Employee {
    private String name;
    private String role;
    private String phoneManager; 

    private Capsule[] collabCapsules = new Capsule[50];
    private int amountCapsules = 0;
    private String[] capsuleTypes = {"TÉCNICO","GESTIÓN","DOMINIO","EXPERIENCIAS"};


    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
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
