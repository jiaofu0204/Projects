package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    Dao personDao;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(Person person, Map<String,Object> map){
        Person person1 =  personDao.getPerson(person.getUsername(),person.getPassword());
        if(person1== null){
            map.put("msg","login fail");
            return "success";
        }else{
            map.put("msg","login success");
        }
        map.put("username", person1.getUsername());
        map.put("dob", person1.getDob());
        map.put("address", person1.getAddress());
        map.put("phone", person1.getPhone());
        map.put("id", person1.getId());
        if(person1.getType().equals("customer")) {
            return "customer";
        }
        else if(person1.getType().equals("seller")){
            return "seller";
        }
        else if(person.getUsername().equals("admin")) {
            map.put("msg", "Welcome admin");
            return "adminSuccess";
        }
        return "success";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/doRegister")
    public String doRegister(Person person, Map<String,Object> map){
        if(person.getType().equals("customer")){
            Customer customer1 = new Customer(person.getUsername(),person.getPassword(),person.getDob(),person.getAddress(),person.getPhone(),person.getType());
            personDao.createCustomer(customer1);
            map.put("msg","register success");
        }
        else if(person.getType().equals("seller")){
            Seller seller1 = new Seller(person.getUsername(),person.getPassword(),person.getDob(),person.getAddress(),person.getPhone(),person.getType());
            personDao.createSeller(seller1);
            map.put("msg","register success");
        }
        else {
            //personDao.insertPerson(person);
            map.put("msg","register fail");
        }

        return "success";
    }
}