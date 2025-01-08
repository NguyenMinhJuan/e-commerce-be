package com.example.ecommerce.model.wrapper;

import com.example.ecommerce.model.Shop;
import com.example.ecommerce.model.User;
import lombok.Data;

@Data
public class ShopWrapper {
    private Shop shop;
    private User user;
}
