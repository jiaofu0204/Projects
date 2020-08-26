package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.MessageToSeller;
import org.springframework.data.repository.query.Param;

//@Repository
public interface MessageToSellerRepository extends CrudRepository<MessageToSeller, Integer> {
    @Query(value = "select * from message_to_seller where message_to_seller.customer_id = :", nativeQuery = true)
    public MessageToSeller findMessageToSellerbyCustomer(@Param("customerid") String customerid);
}