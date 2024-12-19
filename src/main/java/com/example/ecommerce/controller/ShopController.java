package com.example.ecommerce.controller;

import com.example.ecommerce.model.Shop;
import com.example.ecommerce.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public ResponseEntity<List<Shop>> getStores() {
        List<Shop> shops = (List<Shop>) shopService.findAll();
        return ResponseEntity.ok(shops);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> createStore(@RequestBody Shop shop) {
        try {
            shopService.registerShop(shop);
            return ResponseEntity.ok().body("Please wait a few days for us to approve."
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to register!");
        }
    }
}
