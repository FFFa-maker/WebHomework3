package web.homework3.demo.entities;

import java.util.ArrayList;

public class User {
    private String user = "";
    private String password = "";
    private ArrayList<Person> personList;
    public User(String user, String password){
        this.user = user;
        this.password = password;
        this.personList = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }
}
