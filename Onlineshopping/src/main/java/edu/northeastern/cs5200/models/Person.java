package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String dob;
    private String address;
    private String phone;
    private String type;

    public Person() {
    }

    public Person(String username, String password, String dob, String address, String phone, String type){
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.type = type;
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDob(){
        return dob;
    }
    public void setDob(String dob){
        this.dob = dob;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
