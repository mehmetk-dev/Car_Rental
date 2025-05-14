package model;

public class User {

    private int id;
    private String email;
    private String password;
    private int age;
    private boolean isCorporate;
    private boolean isAdmin;


    public User() {
    }

    public User(int id, String email, String password, int age, boolean isCorporate, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.age = age;
        this.isCorporate = isCorporate;
        this.isAdmin = isAdmin;
    }

    public User(String email, String password, int age, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.age = age;
        this.isAdmin = isAdmin;
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

    public boolean isCorporate() {
        return isCorporate;
    }

    public void setCorporate(boolean corporate) {
        isCorporate = corporate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
