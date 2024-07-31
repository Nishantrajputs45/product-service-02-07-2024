package com.example.productservice02072024.controllers;

import com.example.productservice02072024.dtos.FakeStoreDto;
import com.example.productservice02072024.dtos.ProductRequestDto;
import com.example.productservice02072024.dtos.ProductResponseDto;
import com.example.productservice02072024.models.Product;
import com.example.productservice02072024.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/products/{id}")
    public ProductResponseDto getProductDetails(@PathVariable("id") int productId){
        return productService.getSingleProduct(productId);


    }
    @PostMapping("products")
    public ProductResponseDto createNewProduct(@RequestBody ProductRequestDto productRequestDto){
        return productService.addProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );

    }
}
