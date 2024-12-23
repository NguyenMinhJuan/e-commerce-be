package com.example.ecommerce.service.cart;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.repository.ICartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepo cartRepo;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepo.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepo.findById(id);
    }

    @Override
    public void save(Cart cart) {
        cartRepo.save(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public Cart findCartByCustomer(Customer customer) {
        return cartRepo.findCartByCustomer(customer);
    }
}
