package com.example.ecommerce.service.cartitem;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.IGenericService;

public interface ICartItemService extends IGenericService<CartItem, Long> {
    Iterable<CartItem> findAllByCart(Cart cart);
    void addCartItem (Cart cart, Product product);
    Boolean checkIsExistInCart(Cart cart, Product product);
    Boolean increaseQuantity(CartItem cartItem);
    void decreaseQuantity(CartItem cartItem);
    int countByCart(Cart cart);
    void deleteAllByCart(Cart cart);
}
