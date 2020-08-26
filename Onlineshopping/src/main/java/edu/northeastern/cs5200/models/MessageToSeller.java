package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class MessageToSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @ManyToOne()
    private Customer customer;

    @ManyToOne()
    private Seller seller;

    public MessageToSeller() {
    }

    public MessageToSeller(String content, Customer customer, Seller seller) {
        this.content = content;
        this.customer = customer;
        this.seller = seller;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (!customer.getMessageToSellers().contains(this)) {
            customer.getMessageToSellers().add(this);
        }
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        if (!seller.getMessageToSellers().contains(this)) {
            seller.getMessageToSellers().add(this);
        }
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

