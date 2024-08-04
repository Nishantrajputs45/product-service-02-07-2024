package com.example.productservice02072024.services;

import com.example.productservice02072024.dtos.FakeStoreDto;
import com.example.productservice02072024.dtos.ProductRequestDto;
import com.example.productservice02072024.dtos.ProductResponseDto;
import com.example.productservice02072024.models.Product;

public interface ProductService {
    public Product getSingleProduct(int productId);
    Product[] getAllProducts();
    public Product addProduct(
            String title,
            String description,
            String imageURL,
            String category,
            double price
    );


    Product deleteAProduct(int productId);

    Product putProduct(int productId, ProductRequestDto productRequestDto);

    Product patchProduct(int productId, ProductRequestDto productRequestDto);
}
