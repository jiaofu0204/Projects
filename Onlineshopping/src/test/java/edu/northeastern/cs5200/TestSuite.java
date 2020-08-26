package edu.northeastern.cs5200;

import edu.northeastern.cs5200.dao.Dao;
import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repository.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSuite {

	
	@Autowired
    Dao dao;
	
    @Autowired
    PersonRepository personRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MessageToSellerRepository messageToSellerRepository;

    @Autowired
    MessageToCustomerRepository messageToCustomerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    AcceptionRepository acceptionRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
/*
    @Test
    public void A_emptyDatabase() {
        dao.truncateDatabase();
    }*/

    @Test
    public void B_create() {
        Customer bob = new Customer("bob", "123456", "990513", "Boston", "123456789", "customer");
        dao.createCustomer(bob);


        Seller alice = new Seller("alice", "654321", "980724", "Boston", "123434489", "seller");
        dao.createSeller(alice);

        Product book = new Product("book", 15.5, true, "This is a book", alice);
        dao.createProduct(book);

        Product toy = new Product("toy", 20.0, true, "This is a toy", alice);
        dao.createProduct(toy);

        ShoppingCart firstcart = new ShoppingCart(bob);
        dao.createShoppingCart(firstcart);

        dao.addProductToShoppingCart(book, firstcart);
        dao.addProductToShoppingCart(toy, firstcart);

        Orders orderOfBob = new Orders(firstcart);
        //dao.createOrder(orderOfBob);

        //calculate the total price for one shoppingcart/order
        Double totalPrice = 0.0;
        List<Product> products = dao.findProductsForShoppingCart(firstcart);
        for(Product product : products){
            totalPrice += product.getPrice();
        }
        orderOfBob.setTotalPrice(totalPrice);
        orderOfBob.setOrderStatus(OrderStatus.Delivered);
        System.out.println(totalPrice);
        System.out.println("successful test!!");
        dao.createOrder(orderOfBob);

        /*List<Product> products = dao.findProductsForShoppingCart(firstcart);
        for(Product product : products){
            System.out.println(product.getName());
        }*/


        dao.sendMessageToSeller("Hi, I'm Bob.", bob, alice);
        dao.sendMessageToSeller("Nice to meet you.", bob, alice);
        dao.sendMessageToCustomer("Hi, I'm Alice", bob, alice);
        dao.sendMessageToCustomer("Bye", bob, alice);

        //print all the messages sent by a customer(bob)
        List<MessageToSeller> messageToSeller = dao.findAllMessagesSentByCustomer(bob);
        System.out.println("All the messages sent by Bob: ");
        for(MessageToSeller message : messageToSeller){
            System.out.println(message.getContent());
        }
        //print all the messages received by a customer(bob)
        List<MessageToCustomer> messageToCustomer = dao.findAllMessagesReceiveByCustomer(bob);
        System.out.println("All the messages received by Bob: ");
        for(MessageToCustomer message : messageToCustomer){
            System.out.println(message.getContent());
        }
        //print all the messages sent by a seller(alice)
        List<MessageToCustomer> messageToCustomer2 = dao.findAllMessagesSentBySeller(alice);
        System.out.println("All the messages sent by Alice: ");
        for(MessageToCustomer message : messageToCustomer2){
            System.out.println(message.getContent());
        }
        //print all the messages received by a seller(alice)
        List<MessageToSeller> messageToSeller2 = dao.findAllMessagesReceivedBySeller(alice);
        System.out.println("All the messages received by Alice: ");
        for(MessageToSeller message : messageToSeller2){
            System.out.println(message.getContent());
        }

        //print all the products sold by a seller(alice)
        dao.addProductToSeller(book, alice);
        dao.addProductToSeller(toy, alice);
        List<Product> productsOfSeller = dao.findProductsForSeller(alice);
        System.out.println("All the products sold by Alice: ");
        for(Product productOfSeller : productsOfSeller){
            System.out.println(productOfSeller.getName());
        }

        dao.acceptOrderBySeller(orderOfBob, alice);
        //findOrdersForSeller
        System.out.println("The totalPrice of each order of alice is: ");
        List<Orders> orders = dao.findOrdersForSeller(alice);
        for(Orders ordersOfSeller : orders){
            System.out.println(ordersOfSeller.getTotalPrice());
        }
        //findSellersForOrder
        System.out.println("The seller names of orderOfBob are: ");
        List<Seller> sellers = dao.findSellersForOrder(orderOfBob);
        for(Seller sellersOfOrder1 : sellers){
            System.out.println(sellersOfOrder1.getUsername());
        }

        //findUserByUsername
        Person user = dao.findUserByUsername("bob");
        System.out.println(user.getPhone());

    }
}