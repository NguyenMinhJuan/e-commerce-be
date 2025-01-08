package com.example.ecommerce.controller;

import com.example.ecommerce.enums.ShopStatus;
import com.example.ecommerce.model.Shop;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.wrapper.ShopWrapper;
import com.example.ecommerce.service.shop.ShopService;
import com.example.ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Shop>> getStores() {
        List<Shop> shops = (List<Shop>) shopService.findAll();
        return ResponseEntity.ok(shops);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> createStore(@RequestBody ShopWrapper shopWrapper) {
        Shop shop = shopWrapper.getShop();
        User user = shopWrapper.getUser();
        shopService.registerShop(shop, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<?> getShop(@PathVariable long shopId) {
        return ResponseEntity.ok(shopService.findById(shopId));
    }

    @PutMapping("/{shopId}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable long shopId, @PathVariable boolean status) {
        if (status) {
            shopService.setShopStatus(shopService.findById(shopId).get(), ShopStatus.OPERATIONAL);
        } else {
            shopService.setShopStatus(shopService.findById(shopId).get(), ShopStatus.REJECTED);
        }
        return ResponseEntity.ok().body("Updated status successfully!");
    }

    @GetMapping("/getShopStatus/{username}")
    public ResponseEntity<?> getShopStatus(@PathVariable String username) {
        User user = userService.findByUsername(username);
        Shop shop = shopService.findByUser(user);
        if (shop.getShopStatus() == ShopStatus.OPERATIONAL) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (shop.getShopStatus() == ShopStatus.PENDING) {
            return new ResponseEntity<>("Please wait admin to approve", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("You have been rejected!", HttpStatus.BAD_REQUEST);
        }
    }
}
