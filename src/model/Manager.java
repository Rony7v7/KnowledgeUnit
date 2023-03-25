package model;

public class Manager { //INNECESARIO?
    private String name;
    private String phoneNumer;
    
    public Manager(String name, String phoneNumber){
        this.name = name;
        this.phoneNumer = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }
}
