package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.dao.Dao;
import edu.northeastern.cs5200.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    Dao messageDao;

    @RequestMapping("/FindMessageByReceiver")
    public String findmessagebyreceiver(Person person, Model model, Map<String,Object> map){
        //Person person1 = messageDao.findUserByUsername(person.getUsername());
        int id1 = person.getId();
        List<Message> message1 = messageDao.findMessageByReceiver(id1);
        model.addAttribute("messagebyreceiver",message1);
        return "messageFromCustomer";
    }

    @RequestMapping("/FindMessageBySender")
    public String findmessagebysender(Person person, Model model, Map<String,Object> map){
        //Person person1 = messageDao.findUserByUsername(person.getUsername());
        int id1 = person.getId();
        List<Message> message1 = messageDao.findMessageBySender(id1);
        model.addAttribute("messagebysender",message1);
        return "messageFromSeller";
    }

    @RequestMapping("/SendMessage")
    public String sendmessage(Person person, Message message, Map<String,Object> map){
        int id1 = person.getId();
        Person person4 = messageDao.findUserByUsername(person.getUsername());
        int id4 = person4.getId();
        messageDao.sendMessage(message.getContent(), id1, id4);
        map.put("msg","message sent");
        return "sendMessage";
    }

}
