package com.example.ecommerce.service.cart;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.IGenericService;

public interface ICartService extends IGenericService<Cart, Long> {
    Cart findCartByCustomer(Customer customer);
}
