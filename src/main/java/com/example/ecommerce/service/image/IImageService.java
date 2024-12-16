package com.example.ecommerce.service.image;

import com.example.ecommerce.model.Image;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.IGenericService;

public interface IImageService extends IGenericService<Image> {
    Iterable<Image> findAllByProductId(Long productId);
}
