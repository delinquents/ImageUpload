package com.velja.product.imageproductupload.services;

import com.velja.product.imageproductupload.model.Product;
import com.velja.product.imageproductupload.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class ImageServiceImpl implements ImageService {


    private final ProductRepository productRepository;

    public ImageServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void saveImage(Long productId, MultipartFile file) {


    try{
        Product product = productRepository.findById(productId).get();

        Byte[] byteObject = new Byte[file.getBytes().length];

        int i = 0;

        for(byte b : file.getBytes()){
            byteObject[i++] = b;
        }

        product.setImage(byteObject);
        productRepository.save(product);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
