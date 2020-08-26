package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Seller extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<MessageToSeller> messageToSellers = new ArrayList<>();

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<MessageToCustomer> messageToCustomers = new ArrayList<>();

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Acception> acceptions = new ArrayList<>();

    public Seller(){
    }

    public Seller(String username, String password, String dob, String address,
                  String phone, String type){
        super(username, password, dob, address, phone, type);
    }

    public List<MessageToSeller> getMessageToSellers() {
        return messageToSellers;
    }

    public void setMessageToSellers(List<MessageToSeller> messageToSellers) {
        this.messageToSellers = messageToSellers;
    }

    public void setMessageToSellers(MessageToSeller messageToSeller) {
        this.messageToSellers.add(messageToSeller);
        if (messageToSeller.getSeller() != this) {
            messageToSeller.setSeller(this);
        }
    }

    public List<MessageToCustomer> getMessageToCustomers() {
        return messageToCustomers;
    }

    public void setMessageToCustomers(List<MessageToCustomer> messageToCustomers) {
        this.messageToCustomers = messageToCustomers;
    }

    public void setMessageToCustomers(MessageToCustomer messageToCustomer) {
        this.messageToCustomers.add(messageToCustomer);
        if (messageToCustomer.getSeller() != this) {
            messageToCustomer.setSeller(this);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
        if (product.getSeller() != this) {
            product.setSeller(this);
        }
    }

    public List<Acception> getAcceptions() {
        return acceptions;
    }

    public void setAcceptions(List<Acception> acceptions) {
        this.acceptions = acceptions;
    }

    public void setAcceptions(Acception acception) {
        this.acceptions.add(acception);
        if (acception.getSeller() != this) {
            acception.setSeller(this);
        }
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
