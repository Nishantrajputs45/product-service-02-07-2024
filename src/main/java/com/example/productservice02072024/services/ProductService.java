package com.example.productservice02072024.services;

import com.example.productservice02072024.dtos.FakeStoreDto;
import com.example.productservice02072024.dtos.ProductResponseDto;
import com.example.productservice02072024.models.Product;

public interface ProductService {
    public ProductResponseDto getSingleProduct(int productId);
    public ProductResponseDto addProduct(
            String title,
            String description,
            String imageURL,
            String category,
            double price
    );
}
