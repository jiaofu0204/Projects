package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.models.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

//@Repository
public interface FindProductRepository extends CrudRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into product (name, price, seller_id, description, availability) values (:name, :price, :seller_id, :description, :availability) ", nativeQuery = true)
    public void addproduct(@Param("name") String name, @Param("price") double price, @Param("seller_id") int seller_id, @Param("description") String description, @Param("availability") boolean availability);

    @Query(value = "select * from product where product.seller_id = :seller_id", nativeQuery = true)
    public List<Product> findProductBySeller(@Param("seller_id") int seller_id);

    @Query(value = "delete * from product where product.name = :name", nativeQuery = true)
    public void deleteProductByName(@Param("name") String name);

    //@Query(value = "select * from message where message.receiver_id = :receiverid", nativeQuery = true)
    //public Message findMessageByReceiver(@Param("receiverid") int receiverid);
}