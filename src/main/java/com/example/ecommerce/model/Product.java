package com.example.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is mandatory")
    private String name;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}