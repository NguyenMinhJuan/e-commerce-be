package com.example.ecommerce.controller;

import com.example.ecommerce.enums.ShopStatus;
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
            return ResponseEntity.ok().body("Thank you for reaching out. Please allow a few days, and we will get back to you via email as soon as possible!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to register!");
        }
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<?> getShop(@PathVariable long shopId) {
        return ResponseEntity.ok(shopService.findById(shopId));
    }

    @PutMapping("/{shopId}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable long shopId, @PathVariable boolean status) {
        if(status==true){
            shopService.setShopStatus(shopService.findById(shopId).get(),ShopStatus.OPERATIONAL);
        }else {
            shopService.setShopStatus(shopService.findById(shopId).get(),ShopStatus.REJECTED);
        }
        return ResponseEntity.ok().body("Updated status successfully!");
    }
}
