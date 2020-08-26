package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double totalPrice;
    private String customerName;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<Acception> acceptions = new ArrayList<>();

    @OneToOne
    private ShoppingCart shoppingCart;

    public Orders() {
    }

    public Orders(Double totalPrice){
        this.totalPrice = totalPrice;
    }

    public Orders(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Acception> getAcceptions() {
        return acceptions;
    }

    public void setAcceptions(List<Acception> acceptions) {
        this.acceptions = acceptions;
    }

    public void setAcceptions(Acception acception) {
        this.acceptions.add(acception);
        if (acception.getOrder() != this) {
            acception.setOrder(this);
        }
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
