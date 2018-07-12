package com.velja.product.imageproductupload.services;

import com.velja.product.imageproductupload.model.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductService {

    Product findProductById(Long id);
    void deleteProductByName(String name);
    Set<Product> getAllProducts();
    Optional<Product> findByName(String name);
    Product saveProduct(Product product);
}
