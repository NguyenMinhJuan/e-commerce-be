package com.example.ecommerce.service.image;

import com.example.ecommerce.model.Image;
import com.example.ecommerce.repository.IImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired
    IImageRepo imageRepo;

    @Override
    public Iterable<Image> findAll() {
        return imageRepo.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepo.findById(id);
    }

    @Override
    public void save(Image image) {
        imageRepo.save(image);
    }

    @Override
    public void delete(Long id) {
        imageRepo.deleteById(id);
    }


    @Override
    public Iterable<Image> findAllByProductId(Long productId) {
        return imageRepo.findAllByProductId(productId);
    }
}
