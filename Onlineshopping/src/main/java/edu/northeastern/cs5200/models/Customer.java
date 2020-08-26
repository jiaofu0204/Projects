package edu.northeastern.cs5200.models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Customer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<MessageToSeller> messageToSellers = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<MessageToCustomer> messageToCustomers = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public Customer(){
    }

    public Customer(String username, String password, String dob, String address,
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
        if (messageToSeller.getCustomer() != this) {
            messageToSeller.setCustomer(this);
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
        if (messageToCustomer.getCustomer() != this) {
            messageToCustomer.setCustomer(this);
        }
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public void setShoppingCarts(ShoppingCart shoppingCart) {
        this.shoppingCarts.add(shoppingCart);
        if (shoppingCart.getCustomer() != this) {
            shoppingCart.setCustomer(this);
        }
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}

