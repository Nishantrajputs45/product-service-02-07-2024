package com.example.productservice02072024.services;

import com.example.productservice02072024.dtos.FakeStoreDto;
import com.example.productservice02072024.dtos.ProductRequestDto;
import com.example.productservice02072024.dtos.ProductResponseDto;
import com.example.productservice02072024.models.Product;

public interface ProductService {
    public ProductResponseDto getSingleProduct(int productId);
    ProductResponseDto[] getAllProducts();
    public ProductResponseDto addProduct(
            String title,
            String description,
            String imageURL,
            String category,
            double price
    );


    ProductResponseDto deleteAProduct(int productId);

    ProductResponseDto putProduct(int productId, ProductRequestDto productRequestDto);

    ProductResponseDto patchProduct(int productId, ProductRequestDto productRequestDto);
}
