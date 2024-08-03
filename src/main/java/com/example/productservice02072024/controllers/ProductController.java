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
    @GetMapping("/products")
    public ProductResponseDto[] getAllProductsDetails(){
        return productService.getAllProducts();
    }
    @PostMapping("/products")
    public ProductResponseDto createNewProduct(@RequestBody ProductRequestDto productRequestDto){
        return productService.addProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );

    }
    @DeleteMapping("/products/{id}")
    public ProductResponseDto deleteProduct(@PathVariable("id") int productId){
        return productService.deleteAProduct(productId);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto putProduct(@PathVariable("id") int productId,@RequestBody ProductRequestDto productRequestDto){
        return productService.putProduct(productId,productRequestDto);
    }
    @PatchMapping("/products/{id}")
    public ProductResponseDto patchProduct(@PathVariable("id") int productId,@RequestBody ProductRequestDto productRequestDto){
        return productService.patchProduct(productId,productRequestDto);
    }
}
