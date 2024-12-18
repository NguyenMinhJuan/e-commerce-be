package com.example.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

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

    @ElementCollection
    private List<String> images; // List of image URLs

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
