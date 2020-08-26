package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;


@Controller
public class SellerController {
    @Autowired
    Dao sellerDao;

    @RequestMapping("/seller")
    public String seller(){
        return "seller";
    }

    @RequestMapping("/ShowSellerProduct")
    public String showsellerproduct(Seller seller, Model model, Map<String,Object> map){
        int id1 = seller.getId();
        List<Product> product1 = sellerDao.findProductBySeller(id1);
        map.put("sid",id1);
        model.addAttribute("productbyseller",product1);
        return "productsFromSellerPage";
    }

    @RequestMapping("/AddMyProduct")
    public String addmyproduct(Seller seller, Product product,int id3, Map<String,Object> map){
        //int id1 = seller.getId();
        sellerDao.addProduct(product.getName(),product.getPrice(),id3,product.getDescription(),true);
        map.put("msg","added");
        return "geterror";
    }
}
