package com.velja.product.imageproductupload.repositories;


import com.velja.product.imageproductupload.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {

    Optional<Product> findByName(String name);


    void deleteByName(String name);
}
