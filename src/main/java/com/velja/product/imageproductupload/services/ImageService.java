package com.velja.product.imageproductupload.services;


import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImage(Long productId, MultipartFile file);
}
