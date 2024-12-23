package com.example.ecommerce.service.cartitem;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
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

    @Override
    public Iterable<CartItem> findAllByCart(Cart cart) {
        return cartItemRepo.findAllByCart(cart);
    }

    @Override
    public void addCartItem(Cart cart, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setQuantity(1);
        cartItem.setProduct(product);
        cartItemRepo.save(cartItem);
    }

    @Override
    public Boolean checkIsExistInCart(Cart cart, Product product) {
        Iterable<CartItem> cartItems=cartItemRepo.findAllByCart(cart);
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }
}
