package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class Acception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    private Orders orders;

    @ManyToOne()
    private Seller seller;

    public Acception() {
    }

    public Acception(Orders orders, Seller seller) {
        this.orders = orders;
        this.seller = seller;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
        if (!orders.getAcceptions().contains(this)) {
            orders.getAcceptions().add(this);
        }
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        if (!seller.getAcceptions().contains(this)) {
            seller.getAcceptions().add(this);
        }
    }
}

