package model;

import java.util.ArrayList;

/**
 * Employee class
 */
public class Employee {
    private String name;
    private String role;
    private String phoneManager; 

    private ArrayList <String> collabCapsulesIds; //The employee save the capsules id registered in stages

    /**
     * Creates a new Employee object with the given name, role, phone number and empty list of collabCapsulesPos.
     * 
     * @param name the name of the Employee.
     * @param role the role of the Employee.
     * @param phoneNumber the phone number of the Employee.
    */
    public Employee(String name, String role, String phoneNumber) {
        this.name = name;
        this.role = role;
        this.phoneManager = phoneNumber;
        this.collabCapsulesIds = new ArrayList<>();
    }

    //------- Getters -------
    /**
     * Returns the phone number of the manager.
     *
     * @return the phone number of the manager
     */
    public String getPhoneManager() {
        return phoneManager;
    }

    /**
     * Returns the name of the employee.
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the role of the employee.
     *
     * @return the role of the employee
     */   
    public String getRole() {
        return role;
    }

    /** 
     * Returns the Ids of capsules in the project array
     *  
     * @return ArrayList<String> Array list with capsules Ids
     */
    public ArrayList<String> getCollabCapsulesIds() {
        return collabCapsulesIds;
    }

    //-------- Setters -------
    /**
     * Sets the phone number of the manager.
     *
     * @param phoneManager the new phone number of the manager
     */
    public void setPhoneManager(String phoneManager) {
        this.phoneManager = phoneManager;
    }

    /**
     * Adds a capsule id to the list of the employee's capsules.
     *
     * @param capsuleId the Id of the capsule to add
     */
    public void addCapsule(String capsuleId) {
        collabCapsulesIds.add(capsuleId);
    }

}
