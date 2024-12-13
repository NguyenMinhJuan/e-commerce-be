package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productService.findAll();
        if (products.iterator().hasNext()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

//    @PutMapping()
//    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
////        if (productService.findById(product.getId()) != null) {
////            productService.save(product);
////            return new ResponseEntity<>(HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//        return null;
//    }

}
