package com.velja.product.imageproductupload.controllers;

import com.velja.product.imageproductupload.model.Product;
import com.velja.product.imageproductupload.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}/show")
    public String productShow(Model model,@PathVariable String id){

        model.addAttribute("product",productService.findProductById(Long.valueOf(id)));

        return "product/show";
    }
    @GetMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product",new Product());
        return "product/productform";
    }



    @PostMapping("product")
    public String saveOrUpdate(@ModelAttribute Product product){
        Product savedProduct = productService.saveProduct(product);

        return "redirect:/product/"+savedProduct.getId()+"/show";
    }


}
