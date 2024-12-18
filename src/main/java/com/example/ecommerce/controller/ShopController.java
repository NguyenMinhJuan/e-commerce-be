package com.example.ecommerce.controller;

import com.example.ecommerce.model.Shop;
import com.example.ecommerce.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
