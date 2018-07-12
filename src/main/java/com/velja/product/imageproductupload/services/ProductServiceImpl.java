package com.velja.product.imageproductupload.services;

import com.velja.product.imageproductupload.model.Product;
import com.velja.product.imageproductupload.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Transactional
    @Override
    public void deleteProductByName(String name) {
        Optional<Product> optionalProduct = productRepository.findByName(name);
            if(optionalProduct.isPresent()){
                productRepository.deleteByName(name);
            }
                optionalProduct
                    .orElseThrow(() -> new RuntimeException("FAIL!"));
    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        return product;
    }

    @Override
    public Set<Product> getAllProducts() {
        Set<Product> products = new HashSet<>();
            productRepository
                    .findAll()
                    .iterator()
                    .forEachRemaining(products::add);
            return products;
    }

    @Override
    public Optional<Product> findByName(String name) {
        Optional<Product> optionalProduct = productRepository.findByName(name);
            optionalProduct
                    .orElseThrow(() -> new RuntimeException("Product is not found!"));
            return optionalProduct;
    }

    @Override
    public Product saveProduct(Product product) {
      return  productRepository.save(product);
    }
}
