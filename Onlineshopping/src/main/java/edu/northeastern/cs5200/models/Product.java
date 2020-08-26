package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;

    private Boolean availability;
    private String description;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private ShoppingCart shoppingCart;

    public Product() {
    }

    public Product(String name, Double price, Boolean availability, String description, Seller seller){
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.description = description;
        this.seller = seller;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Boolean getAvailability() {
        return availability;
    }
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;

        if(!shoppingCart.getProducts().contains(this)) {
            shoppingCart.getProducts().add(this);
        }
    }
    
}
