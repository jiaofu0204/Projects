package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Product;

//@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(value = "select * from product where product.name = :name", nativeQuery = true)
    public Product findProductByName(@Param("name") String name);

    @Query(value = "delete * from product where product.name = :name", nativeQuery = true)
    public void deleteProductByName(@Param("name") String name);

}
