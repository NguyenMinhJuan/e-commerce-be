package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Shop;
import com.example.ecommerce.service.category.ICategoryService;
import com.example.ecommerce.service.product.IProductService;
import com.example.ecommerce.service.shop.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IShopService shopService;

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

    @GetMapping({"/category/{id}"})
    public ResponseEntity<Iterable<Product>> findALlByCategory(@PathVariable long id) {
        Iterable<Product> products = productService.findAllByCategory(categoryService.findById(id).get());
        return new ResponseEntity<>(products, HttpStatus.OK);
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

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<Iterable<Product>> getProductByShop(@PathVariable long id) {
        try {
            Shop shop = shopService.findById(id).get();
            return new ResponseEntity<>(productService.findAllByShop(shop), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
