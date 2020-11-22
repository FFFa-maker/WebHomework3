package web.homework3.demo.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String user = "";
    private String password = "";
    private final HashMap<String,Person> personMap;
    public User(String user, String password){
        this.user = user;
        this.password = password;
        this.personMap = new HashMap<>();
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

    public HashMap<String,Person> getPersonMap() {
        return personMap;
    }
}
