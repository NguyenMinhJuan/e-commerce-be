package com.example.ecommerce.controller;

import com.example.ecommerce.model.Image;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Shop;
import com.example.ecommerce.service.category.ICategoryService;
import com.example.ecommerce.service.image.IImageService;
import com.example.ecommerce.service.product.IProductService;
import com.example.ecommerce.service.shop.IShopService;
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
    private IImageService imageService;

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

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isPresent()) {
            updatedProduct.setId(id);
            productService.save(updatedProduct);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Iterable<Product>> findAllProductsByCategory(@PathVariable long id) {
        Iterable<Product> products = productService.findAllByCategory(categoryService.findById(id).get());
        return new ResponseEntity<>(products, HttpStatus.OK);
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

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent() && !file.isEmpty()) {
            Image image = new Image();
            image.setProduct(product.get());
            image.setImgUrl("uploads/" + file.getOriginalFilename());
            imageService.save(image);
            return new ResponseEntity<>("Image uploaded successfully: " + image.getImgUrl(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found or file is empty", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<Iterable<Product>> getProductsByShop(@PathVariable long id) {
        try {
            Shop shop = shopService.findById(id).get();
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
}
