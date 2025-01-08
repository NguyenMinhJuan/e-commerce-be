package com.example.ecommerce.controller;

import com.example.ecommerce.model.*;
import com.example.ecommerce.service.cart.CartService;
import com.example.ecommerce.service.category.ICategoryService;
import com.example.ecommerce.service.customer.CustomerService;
import com.example.ecommerce.service.product.IProductService;
import com.example.ecommerce.service.shop.IShopService;
import com.example.ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IShopService shopService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;

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

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestPart("product") Product product,
            @RequestPart("files") MultipartFile[] files) {
        try {
            Product savedProduct = productService.createProduct(product, files);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping({"/category/{id}"})
    public ResponseEntity<Iterable<Product>> findALlProductsByCategory(@PathVariable long id) {
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

    @GetMapping("/shop/{name}")
    public ResponseEntity<Iterable<Product>> getProductsByShop(@PathVariable String name) {
        try {
            User user = userService.findByUsername(name);
            Shop shop = shopService.findByUser(user);
            return new ResponseEntity<>(productService.findAllByShop(shop), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("checkInStock/{productId}")
    public ResponseEntity<?> checkInStock(@PathVariable long productId) {
        try {
            Optional<Product> product = productService.findById(productId);
            if (productService.isProductInStock(product.get())) {
                return new ResponseEntity<>("In stock", HttpStatus.OK);
            }
            return new ResponseEntity<>("Out of stock", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateProductStock/{username}")
    public ResponseEntity<?> updateProductStock(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            Customer customer = customerService.findByUser(user);
            Cart cart = cartService.findCartByCustomer(customer);
            productService.updateProductStock(cart);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }


}
