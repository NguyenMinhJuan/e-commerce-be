package com.example.ecommerce.repository;

import com.example.ecommerce.model.Image;
import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepo extends JpaRepository<Image, Long> {
    Iterable<Image> findAllByProductId(Long productId);
}
