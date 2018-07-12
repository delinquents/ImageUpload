package com.velja.product.imageproductupload.controllers;


import com.velja.product.imageproductupload.model.Product;
import com.velja.product.imageproductupload.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {


    private final ProductService productService;

    @Autowired
    public IndexController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"","/","home"})
    public String home(Model model){

        log.info("I am in index controller.");
        model.addAttribute("products",productService.getAllProducts());

        return "index";
    }

}
