package com.example.ecommerce.repository;

import com.example.ecommerce.model.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepo {
    Cart findCartByUserId(Long userId);
}
