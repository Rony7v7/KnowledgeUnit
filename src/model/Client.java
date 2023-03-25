package model;

public class Client {  //INNECESARIO?
    private String name;
    private String phoneNumer;
    
    public Client(String name, String phoneNumber){
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
