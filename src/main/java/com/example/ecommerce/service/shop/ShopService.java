package com.example.ecommerce.service.shop;

import com.example.ecommerce.model.Shop;

import com.example.ecommerce.repository.IShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopService implements IShopService {

    @Autowired
    private IShopRepo shopRepository;

    @Override
    public Iterable<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public void save(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }
}