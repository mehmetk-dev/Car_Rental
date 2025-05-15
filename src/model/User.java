package model;

import model.enums.UserType;

public class User {

    private int id;
    private String email;
    private String password;
    private int age;
    private UserType userType;


    public User(String email, String password, int age, UserType userType) {
        this.email = email;
        this.password = password;
        this.age = age;
        this.userType = userType;
    }

    public User() {
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
