package com.velja.product.imageproductupload.controllers;

import com.velja.product.imageproductupload.model.Product;
import com.velja.product.imageproductupload.services.ImageService;
import com.velja.product.imageproductupload.services.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {


    private final ProductService productService;
    private final ImageService imageService;

    public ImageController(ProductService productService,ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @GetMapping("product/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){

        model.addAttribute("product",productService.findProductById(Long.valueOf(id)));

        return "product/imagefileupload";
    }


    @GetMapping("product/{id}/productimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) {

        Product product = productService.findProductById(Long.valueOf(id));
        try {
            if (product == null) {
                throw new RuntimeException("there is no product with this id : " + id);
            }


            byte[] byteArray = new byte[product.getImage().length];
            int i = 0;

            for (Byte wrappedByte : product.getImage()) {

                byteArray[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    @PostMapping("product/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImage(Long.valueOf(id),file);

        return "redirect:/product/" + Long.valueOf(id) + "/show";

    }



}
