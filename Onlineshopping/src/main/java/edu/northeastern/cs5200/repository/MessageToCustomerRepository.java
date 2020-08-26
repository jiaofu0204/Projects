package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.models.MessageToCustomer;
import edu.northeastern.cs5200.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//@Repository
public interface MessageToCustomerRepository extends CrudRepository<MessageToCustomer, Integer> {
    @Query(value = "select * from product where product.name = :name", nativeQuery = true)
    public Product findProductByName(@Param("name") String name);
}