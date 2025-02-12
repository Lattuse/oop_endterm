package kz.lattuse.oop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.lattuse.oop.dbconnection.DbConnection;
import kz.lattuse.oop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class MyController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/hello")
    public String myHelloListener(){
        return "Hello world!!!";
    }

    @GetMapping("/user")
    public String myUserListener(){
        String jsonText = null;
        User user1 = new User("Sultanbek", "Male", 0);

        try {
            jsonText = objectMapper.writeValueAsString(user1);
        } catch (JsonProcessingException e) {
            System.out.println("Something is wrong bro " + e.toString());
        }

        return jsonText;
    }

    @PostMapping("/custom_user")
    public String myCustomUserListener(@RequestParam String name, String gender, int id){
        String jsonText = null;
        User user1 = new User(name, gender, id);

        try {
            jsonText = objectMapper.writeValueAsString(user1);
        } catch (JsonProcessingException e) {
            System.out.println("Something is wrong bro " + e.toString());
        }

        return jsonText;
    }


    @GetMapping("/allUsers")
    public String allUsersListener() {
        DbConnection myConnection = new DbConnection();
        Connection con = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            con = myConnection.connection();
            users = myConnection.getAllUsersRS(con);
        } catch (Exception e) {
            System.out.println("EXCEPTION 1");
        }

        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            System.out.println("Some error with users");
        }
        System.out.println(jsonData);
        return jsonData;
    }

    @PostMapping("/findUserByName")
    public String listener4(@RequestParam String name) {
        DbConnection myConnection = new DbConnection();
        Connection con = null;
        ArrayList<User> s1 = null;

        try {
            con = myConnection.connection();
        } catch (Exception e) {
            System.out.println("EXCEPTION 1");
        }

        try {
            s1 = myConnection.findUserByName(con, name);
        } catch (Exception e) {
            System.out.println("EXCEPTION 2");
        }

        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(s1);
        } catch (JsonProcessingException e) {
            System.out.println("Some error with user");
        }

        return jsonData;
    }


    @PostMapping("/findUserByID")
    public String findUserByIdListener(@RequestParam int id) {
        DbConnection myConnection = new DbConnection();
        Connection con = null;
        User s1 = null;

        try {
            con = myConnection.connection();
        } catch (Exception e) {
            System.out.println("EXCEPTION 1");
        }

        try {
            s1 = myConnection.findUserById(con, id);
        } catch (Exception e) {
            System.out.println("EXCEPTION 2");
        }

        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(s1);
        } catch (JsonProcessingException e) {
            System.out.println("Some error with user");
        }

        return jsonData;
    }


    @PostMapping("/index/createStudent")
    public String createStudent(@RequestParam String name, @RequestParam String gender, @RequestParam int id) {
        DbConnection myConnection = new DbConnection();
        Connection con = null;
        User s1 = new User(name, gender, id);
        String jsonData = null;

        try {
            con = myConnection.connection();
            // myConnection.createStudent(con, s1);
        } catch (Exception e) {
            System.out.println("EXCEPTION 1");
        }

        try {
            jsonData = objectMapper.writeValueAsString(myConnection.createUser(con, s1));
        } catch (JsonProcessingException e) {
            System.out.println("Some error with user");
        } catch (SQLException e2) {
            System.out.println("Some error with sql user");
        }

        return jsonData;
    }


    @PostMapping("/updateUser")
    public String updateUser(@RequestParam int id, @RequestParam String newName, @RequestParam String newGender, @RequestParam int newId) {
        DbConnection myConnection = new DbConnection();
        Connection con = null;
        User s1 = null;
        String jsonData = null;

        try {
            con = myConnection.connection();
            s1 = myConnection.findUserById(con, id);
            int oldId = s1.getId();
            System.out.println(s1);
            s1.setName(newName);
            s1.setGender(newGender);
            s1.setId(newId);
            System.out.println(s1);
            con = myConnection.connection();
            myConnection.updateUser(con, s1, oldId);
        } catch (Exception e) {
            System.out.println("EXCEPTION 1");
        }

        return jsonData;
    }


}
