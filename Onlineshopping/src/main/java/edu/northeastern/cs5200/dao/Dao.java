package edu.northeastern.cs5200.dao;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Dao {

    public Dao(){
    }

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

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    FindProductRepository findProductRepository;

    public void truncateDatabase() {
        acceptionRepository.deleteAll();
        messageToSellerRepository.deleteAll();
        messageToCustomerRepository.deleteAll();
        shoppingCartRepository.deleteAll();
        productRepository.deleteAll();
        sellerRepository.deleteAll();
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        personRepository.deleteAll();
    }

    //register
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public void addProductToShoppingCart(Product product, ShoppingCart shoppingCart) {
        //product.setShoppingCart(shoppingCart);
        shoppingCart.setProducts(product);
        //productRepository.save(product);
        //shoppingCartRepository.save(shoppingCart);
    }

    public void acceptOrderBySeller(Orders order, Seller seller) {
        Acception acception = new Acception(order, seller);
        acceptionRepository.save(acception);
        order.setAcceptions(acception);
        seller.setAcceptions(acception);
        orderRepository.save(order);
        sellerRepository.save(seller);
    }

    //a customer sends a message to a seller
    public void sendMessageToSeller(String content, Customer customer, Seller seller) {
        MessageToSeller messageToSeller = new MessageToSeller(content, customer, seller);
        messageToSellerRepository.save(messageToSeller);
        customer.setMessageToSellers(messageToSeller);
        seller.setMessageToSellers(messageToSeller);
        customerRepository.save(customer);
        sellerRepository.save(seller);
    }

    //a seller sends a message to a customer
    public void sendMessageToCustomer(String content, Customer customer, Seller seller) {
        MessageToCustomer messageToCustomer = new MessageToCustomer(content, customer, seller);
        messageToCustomerRepository.save(messageToCustomer);
        customer.setMessageToCustomers(messageToCustomer);
        seller.setMessageToCustomers(messageToCustomer);
        customerRepository.save(customer);
        sellerRepository.save(seller);
    }

    public List<Person> findAllUsers(){
        return (List<Person>)personRepository.findAll();
    }

    public Person findUserByUsername(String username){
        return personRepository.findUserByUsername(username);
    }

    public void deleteUserByUsername(String username){
        personRepository.deleteUserByUsername(username);
    }


    public List<Customer> findAllCustomers(){
        return (List<Customer>)customerRepository.findAll();
    }

    public List<Seller> findAllSellers(){
        return (List<Seller>)sellerRepository.findAll();
    }

    public List<Orders> findAllOrders(){
        return (List<Orders>)orderRepository.findAll();
    }

    public List<Product> findAllProducts(){
        return (List<Product>)productRepository.findAll();
    }

    public List<ShoppingCart> findAllShoppingCarts(){
        return (List<ShoppingCart>)shoppingCartRepository.findAll();
    }

    public List<Product> findProductsForSeller(Seller seller){
        return (List<Product>) seller.getProducts();
    }

    public List<ShoppingCart> findShoppingCartsForCustomer(Customer customer){
        return (List<ShoppingCart>) customer.getShoppingCarts();
    }

    public List<Product> findProductsForShoppingCart(ShoppingCart shoppingCart){
        return (List<Product>) shoppingCart.getProducts();
    }

    //find all the messages sent by a customer
    public List<MessageToSeller> findAllMessagesSentByCustomer(Customer customer){
        return (List<MessageToSeller>) customer.getMessageToSellers();
    }

    //find all the messages received by a customer
    public List<MessageToCustomer> findAllMessagesReceiveByCustomer(Customer customer){
        return (List<MessageToCustomer>) customer.getMessageToCustomers();
    }

    //find all the messages sent by a seller
    public List<MessageToCustomer> findAllMessagesSentBySeller(Seller seller){
        return (List<MessageToCustomer>) seller.getMessageToCustomers();
    }

    //find all the messages received by a seller
    public List<MessageToSeller> findAllMessagesReceivedBySeller(Seller seller){
        return (List<MessageToSeller>) seller.getMessageToSellers();
    }

    public List<Orders> findOrdersForSeller(Seller seller){
        List<Acception> acceptions = seller.getAcceptions();
        List<Orders> orders = new ArrayList<Orders>();
        for(Acception a : acceptions ) {
            orders.add(a.getOrder());
        }
        return orders;
    }

    public List<Seller> findSellersForOrder(Orders order){
        List<Acception> acceptions = order.getAcceptions();
        List<Seller> sellers = new ArrayList<Seller>();
        for(Acception a : acceptions ) {
            sellers.add(a.getSeller());
        }
        return sellers;
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }
    public void deleteCustomerById(Integer id){
        customerRepository.deleteById(id);
    }

    public void deleteSeller(Seller seller){
        sellerRepository.delete(seller);
    }
    public void deleteSellerById(Integer id){
        sellerRepository.deleteById(id);
    }

    public void deleteOrder(Orders order){
        orderRepository.delete(order);
    }
    public void deleteOrderById(Integer id){
        orderRepository.deleteById(id);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }
    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }

    public void deleteShoppingCart(ShoppingCart shoppingCart){
        shoppingCartRepository.delete(shoppingCart);
    }
    public void deleteShoppingCartById(Integer id){
        shoppingCartRepository.deleteById(id);
    }

    public Customer updateCustomer(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        customer.setAddress("whatever");
        return customerRepository.save(customer);
    }

    public void addProductToSeller(Product product, Seller seller) {
        seller.setProducts(product);
    }

    public void updateProductDescription(Product product, String content){
        product.setDescription(content);
    }

    public List<Product> findProductsForOrder(Orders order){
        ShoppingCart shoppingCart = order.getShoppingCart();
        return findProductsForShoppingCart(shoppingCart);
    }

    //findProductByName
    public Product findProductByName(String name){
        return productRepository.findProductByName(name);
    }

    //deleteProductByName
    public void deleteProductByName(String name){
        productRepository.deleteProductByName(name);
    }

    public Person getPerson(String username, String password) {
        return personRepository.findByUsernameAndPassword(username,password);
    }

    public Person getPersonbyUsername(String username){
        return personRepository.findUserByUsername(username);
    }

    public Person getPersonbyPassword(String password){
        return personRepository.findUserByPassword(password);
    }

    public void insertPerson(Person person) {
        personRepository.save(person);
    }

    //sendMessage
    public void sendMessage(String content, int senderid, int receiverid){
        messageRepository.sendMessage(content, senderid, receiverid);
    }

    //findMessageBySender
    public List<Message> findMessageBySender(int senderid){
        return messageRepository.findMessageBySender(senderid);
    }

    //findMessageByReceiver
    public List<Message> findMessageByReceiver(int receiverid){
        return messageRepository.findMessageByReceiver(receiverid);
    }

    public List<Product> findProductBySeller(int sellerid){
        return findProductRepository.findProductBySeller(sellerid);
    }

    public void addProduct(String name, double price, int seller_id, String description, boolean availability){
        findProductRepository.addproduct(name, price, seller_id, description, availability);
    }

    //这个函数是把createShoppingCart和addProduct合二为一了，
//createAndAddProductToShoppingCart
    public void createAndAddProductToShoppingCart(String customerName, String productName){
        shoppingCartRepository.createAndAddProductToShoppingCart(customerName, productName);
    }
    //这个函数是输入一个customerName，返回这个customer购物车里所有的product的name
    //findProductNamesInShoppingCartByCustomerName
    public List<String> findProductNamesInShoppingCartByCustomerName(String customerName){
        return shoppingCartRepository.findProductNamesInShoppingCartByCustomerName(customerName);
    }

    //createOrder
    public void createOrder(String customerName, String orderStatus){
        orderRepository.createOrder(customerName, orderStatus);
    }

    //insertTotalPrice
    public void insertTotalPrice(String customerName, String customerName2){
        orderRepository.insertTotalPrice(customerName, customerName2);
    }

}

