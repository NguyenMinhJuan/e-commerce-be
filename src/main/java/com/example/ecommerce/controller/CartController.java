package com.example.ecommerce.controller;

import com.example.ecommerce.model.*;
import com.example.ecommerce.service.cart.ICartService;
import com.example.ecommerce.service.cartitem.ICartItemService;
import com.example.ecommerce.service.customer.ICustomerService;
import com.example.ecommerce.service.product.IProductService;
import com.example.ecommerce.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/{username}")
    public ResponseEntity<Iterable<CartItem>> findAllCartItemByUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        Customer customer=customerService.findByUser(user);
        Cart cart=cartService.findCartByCustomer(customer);
        return new ResponseEntity<>(cartItemService.findAllByCart(cart),HttpStatus.OK);
    }

    @PostMapping("/add/{productId}/{username}")
    public ResponseEntity<?> addCartItem(@PathVariable Long productId, @PathVariable String username){
        try {
            User user = userService.findByUsername(username);
            Customer customer=customerService.findByUser(user);
            Cart cart=cartService.findCartByCustomer(customer);
            Optional<Product> product = productService.findById(productId);
            cartItemService.addCartItem(cart,product.get());
            return new ResponseEntity<>("Add to cart successfully!",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Add to cart to failed",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/isProductExistInCart/{username}/{productId}")
    public ResponseEntity<?> isProductExistInCart(@PathVariable String username,@PathVariable Long productId){
        User user = userService.findByUsername(username);
        Customer customer = customerService.findByUser(user);
        Cart cart =cartService.findCartByCustomer(customer);
        Product product = productService.findById(productId).get();
        if (cartItemService.checkIsExistInCart(cart,product)) {
            return new ResponseEntity<>("Product exist in cart",HttpStatus.OK);
        }else
            return new ResponseEntity<>("Product dont exist in cart",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/increaseQuantityByOne/{productId}/{username}")
    public ResponseEntity<?> increaseQuantityByOne(@PathVariable Long productId, @PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            Customer customer = customerService.findByUser(user);
            Cart cart = cartService.findCartByCustomer(customer);
            Iterable<CartItem> cartItems = cartItemService.findAllByCart(cart);
            Product product = productService.findById(productId).get();

            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getId().equals(product.getId())) {
                    // Check if increasing quantity would exceed product's available quantity
                    if (cartItem.getQuantity() >= cartItem.getProduct().getQuantity()) {
                        return new ResponseEntity<>("Cannot increase quantity: Not enough stock available", HttpStatus.BAD_REQUEST);
                    }
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    cartItemService.save(cartItem);
                    return new ResponseEntity<>(cartItem, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Item not found in cart", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating cart: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long itemId) {
        Optional<CartItem> optionalItem = cartItemService.findById(itemId);

        if (optionalItem.isEmpty()) {
            return new ResponseEntity<>("Cart item not found", HttpStatus.NOT_FOUND);
        }

        try {
            cartItemService.delete(itemId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting cart item: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/increase/{itemId}")
    public ResponseEntity<?> increaseQuantity(@PathVariable Long itemId) {
        CartItem cartItem = cartItemService.findById(itemId).get();
        cartItemService.increaseQuantity(cartItem);
        cartItemService.save(cartItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/decrease/{itemId}")
    public ResponseEntity<?> decreaseQuantity(@PathVariable Long itemId) {
        CartItem cartItem = cartItemService.findById(itemId).get();
        cartItemService.decreaseQuantity(cartItem);
        cartItemService.save(cartItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
