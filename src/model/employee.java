package model;

import java.util.ArrayList;

public class Employee {
    private String name;
    private String role;
    private String phoneManager; 

    private ArrayList <String> collabCapsulesPos; //The employee save the capsules position registered in stages


    public Employee(String name, String role) { //SET phone manager
        this.name = name;
        this.role = role;

        this.collabCapsulesPos = new ArrayList<>();
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

    //-------- Setters -------
    public void setPhoneManager(String phoneManager) {
        this.phoneManager = phoneManager;
    }

    public void addCapsule(String capsulePos) {
        collabCapsulesPos.add(capsulePos);
    }


}
