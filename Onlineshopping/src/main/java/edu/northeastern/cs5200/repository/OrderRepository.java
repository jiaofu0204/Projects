package edu.northeastern.cs5200.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Orders;

import javax.transaction.Transactional;

//@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into orders (customer_name, order_status) values (:customerName, :orderStatus) ", nativeQuery = true)
    public void createOrder(@Param("customerName") String customerName, @Param("orderStatus") String orderStatus);

    @Transactional
    @Modifying
    @Query(value = "update orders set total_price= (select sum(price) from product where product.name in (select product_name from shopping_cart where customer_name = :customerName) ) where orders.customer_name = :customerName2", nativeQuery = true)
    public void insertTotalPrice(@Param("customerName") String customerName, @Param("customerName2") String customerName2);

}