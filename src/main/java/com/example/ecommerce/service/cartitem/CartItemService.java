package com.example.ecommerce.service.cartitem;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.repository.ICartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService implements ICartItemService {
    @Autowired
    ICartItemRepo cartItemRepo;

    @Override
    public Iterable<CartItem> findAll() {
        return cartItemRepo.findAll();
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepo.findById(id);
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepo.save(cartItem);
    }

    @Override
    public void delete(Long id) {
        cartItemRepo.deleteById(id);
    }
}
