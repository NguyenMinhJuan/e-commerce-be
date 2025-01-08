package com.example.ecommerce.service.product;

import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.IImageRepo;
import com.example.ecommerce.repository.IProductRepo;
import com.example.ecommerce.service.cartitem.ICartItemService;
import com.example.ecommerce.service.fileupload.FileUploadService;
import com.example.ecommerce.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepo productRepo;

    @Autowired
    ICartItemService cartItemService;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    IImageRepo imageRepo;

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
    public Boolean isProductInStock(Product product) {
        if(product.getQuantity()==0){
            return false;
        }
        return true;
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepo.findAllByCategory(category);
    }

    @Override
    public void updateProductStock(Cart cart) {
        Iterable<CartItem> cartItems = cartItemService.findAllByCart(cart);
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();

            // Calculate new quantity
            int currentQuantity = product.getQuantity();
            int orderedQuantity = cartItem.getQuantity();
            int newQuantity = currentQuantity - orderedQuantity;

            // Update product quantity
            product.setQuantity(newQuantity);

            // Save updated product
            productRepo.save(product);
        }
    }

    @Override
    public Product createProduct(Product product, MultipartFile[] files) {
        Product savedProduct = productRepo.save(product);

        // Xử lý và lưu các ảnh
        if (files != null) {
            for (MultipartFile file : files) {
                String fileName = fileUploadService.saveFile(file);

                Image image = new Image();
                image.setProduct(savedProduct);
                image.setImgUrl(fileName);
                imageRepo.save(image);
            }
        }

        return savedProduct;
    }
}
