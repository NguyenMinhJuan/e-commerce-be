package com.example.ecommerce.service.product;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.IGenericService;

public interface IProductService extends IGenericService<Product>{
    Iterable<Product> findAllByCategory(Category category);
}
