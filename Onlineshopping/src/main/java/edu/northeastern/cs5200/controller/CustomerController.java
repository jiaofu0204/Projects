package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.dao.Dao;
import edu.northeastern.cs5200.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    Dao customerDao;

    @RequestMapping("/customer")
    public String customer(){
        return "customer";
    }

    @RequestMapping("/findproduct")
    public String findproduct(Product product,String username, Map<String,Object> map){
        Product product1 = customerDao.findProductByName(product.getName());
        if(product1 == null){
            map.put("msg","can't find the product");
            return "geterror";
        }
        else{
            map.put("msg","product found");
        }
        map.put("product_name", product1.getName());
        map.put("description", product1.getDescription());
        map.put("price", product1.getName());
        //map.put("seller_id", product1.getSeller().getId());
        map.put("availability", product1.getAvailability());
        map.put("username",username);
        return "product";
    }

    @RequestMapping("/addtocart")
    public String addtocart(Product product, Person person, Map<String,Object> map){
        customerDao.createAndAddProductToShoppingCart(person.getUsername(),product.getName());
        map.put("msg","product added to your shopping cart");
        return "geterror";
    }

    @RequestMapping("/MyShoppingCart")
    public String myshoppingcart(Person person, Model model){
        List<String> productname = customerDao.findProductNamesInShoppingCartByCustomerName(person.getUsername());
        model.addAttribute("product_name",productname);
        return "shoppingCart";
    }
}
