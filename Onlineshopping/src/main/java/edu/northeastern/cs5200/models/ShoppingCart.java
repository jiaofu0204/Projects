package edu.northeastern.cs5200.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String productName;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Orders orders;


    public ShoppingCart() {
    }

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
        if (product.getShoppingCart() != this) {
            product.setShoppingCart(this);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

        if(!customer.getShoppingCarts().contains(this)) {
        	customer.getShoppingCarts().add(this);
        }
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
