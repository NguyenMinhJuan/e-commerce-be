package com.example.ecommerce.controller;

import com.example.ecommerce.model.Image;
import com.example.ecommerce.service.image.IImageService;
import com.example.ecommerce.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @Autowired
    private IProductService productService;

    @GetMapping("{id}")
     public ResponseEntity<Iterable<Image>> findAllByProduct(@PathVariable long id){
        return new ResponseEntity<>(imageService.findAllByProductId(id), HttpStatus.OK);
    }
}
