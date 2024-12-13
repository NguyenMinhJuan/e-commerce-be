package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.cart.ICartService;
import com.example.ecommerce.service.cartitem.ICartItemService;
import com.example.ecommerce.service.customer.ICustomerService;
import com.example.ecommerce.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/{username}")
    public ResponseEntity<Iterable<CartItem>> findAllCartItemByUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        Customer customer=customerService.findByUser(user);
        Cart cart=cartService.findCartByCustomer(customer);
        return new ResponseEntity<>(cartItemService.findAllByCart(cart),HttpStatus.OK);
    }
}
