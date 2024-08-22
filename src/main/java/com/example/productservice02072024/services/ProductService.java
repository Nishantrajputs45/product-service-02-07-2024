package com.example.productservice02072024.services;

import com.example.productservice02072024.dtos.FakeStoreDto;
import com.example.productservice02072024.dtos.ProductRequestDto;
import com.example.productservice02072024.dtos.ProductResponseDto;
import com.example.productservice02072024.models.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    public Product getSingleProduct(int productId);
    Page<Product> getAllProducts(int pageSize,int pageNumber,String fieldName);
    public Product addProduct(
            String title,
            String description,
            String imageURL,
            String category,
            double price
    );


    Product deleteAProduct(int productId);

    Product putProduct(int productId,String title,String description,String imageUrl,String category, double price);

    Product patchProduct(int productId, String title,String description,String imageUrl,String category, double price);
}
