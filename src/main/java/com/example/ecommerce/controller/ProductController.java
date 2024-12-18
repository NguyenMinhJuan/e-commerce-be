package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.category.ICategoryService;
import com.example.ecommerce.service.product.IProductService;
import com.example.ecommerce.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private ProductService productServicee;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productService.findAll();
        if (products.iterator().hasNext()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping({"/category/{id}"})
    public ResponseEntity<Iterable<Product>> findALlByCategory(@PathVariable long id) {
        Iterable<Product> products= productService.findAllByCategory(categoryService.findById(id).get());
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
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productService.findById(id);

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productServicee.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }
        if (existingProduct.isPresent()) {
            updatedProduct.setId(id); // Ensure the ID is set for update
            productService.save(updatedProduct);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent() && !file.isEmpty()) {
            String imageUrl = "uploads/" + file.getOriginalFilename(); // Simulate storing the image
            product.get().getImages().add(imageUrl);
            productService.save(product.get());
            return new ResponseEntity<>("Image uploaded successfully: " + imageUrl, HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found or file is empty", HttpStatus.BAD_REQUEST);
    }
}
