package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Product;
import edu.northeastern.cs5200.models.ShoppingCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

//@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into shopping_cart (customer_name, product_name) values (:customerName, :productName) ", nativeQuery = true)
    public void createAndAddProductToShoppingCart(@Param("customerName") String customerName, @Param("productName") String productName);

    @Query(value = "select product_name from shopping_cart where shopping_cart.customer_name = :customerName", nativeQuery = true)
    public List<String> findProductNamesInShoppingCartByCustomerName(@Param("customerName") String customerName);
}