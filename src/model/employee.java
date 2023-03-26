package model;

public class Employee {
    private String name;
    private String role;
    private String phoneManager;

    //private Capsule[] capsules = new Capsule[50]; 

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public void setPhoneManager(String phoneManager) {
        this.phoneManager = phoneManager;
    }

    public String getPhoneManager() {
        return phoneManager;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
