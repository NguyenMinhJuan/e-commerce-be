package com.example.ecommerce.service.product;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Shop;
import com.example.ecommerce.service.IGenericService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService extends IGenericService<Product, Long>{
    Iterable<Product> findAllByCategory(Category category);
    List<Product> searchProducts(String keyword);
    Iterable<Product> findAllByShop(Shop shop);
    Boolean isProductInStock (Product product);
    void updateProductStock(Cart cart);
    Product createProduct(Product product, MultipartFile[] files);
}
