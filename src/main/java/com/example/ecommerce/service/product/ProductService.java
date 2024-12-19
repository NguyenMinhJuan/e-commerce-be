package com.example.ecommerce.service.product;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Shop;
import com.example.ecommerce.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepo productRepo;

    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepo.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public Iterable<Product> findAllByShop(Shop shop) {
        return productRepo.findAllByShop(shop);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepo.findAllByCategory(category);
    }


}
