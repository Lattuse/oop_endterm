package kz.lattuse.oop.entities;


public class User{
    private int id;
    private String name;
    private String gender;

    public User(String name, String gender, int id) {
        this.name = name;
        this.gender = gender;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    @Override
    public String toString() {
        return "User {name='" + name + "', userID=" + id + ", gender='" + gender + "'}";
    }
}

