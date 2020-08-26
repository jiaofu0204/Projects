package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @ManyToOne()
    private Person sender;

    @ManyToOne()
    private Person receiver;

    public Message() {
    }

    public Message(String content, Person sender, Person receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

