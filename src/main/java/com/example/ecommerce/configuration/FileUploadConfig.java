package com.example.ecommerce.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileUploadConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}

//@Configuration
//public class FileUploadConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // Chỉ định đường dẫn tuyệt đối đến thư mục uploads trên Windows
//        registry.addResourceHandler("/uploads/**")
//                .addResourceLocations("file:C:/Users/Admin/Desktop/Back-end/-e-commerce-back-end/uploads/");
//    }
//}

