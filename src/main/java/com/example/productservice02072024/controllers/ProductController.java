package com.example.productservice02072024.controllers;

import com.example.productservice02072024.dtos.FakeStoreDto;
import com.example.productservice02072024.dtos.ProductRequestDto;
import com.example.productservice02072024.dtos.ProductResponseDto;
import com.example.productservice02072024.models.Product;
import com.example.productservice02072024.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductDetails(@PathVariable("id") int productId){
        Product product= productService.getSingleProduct(productId);
        ProductResponseDto productResponseDto=convertProductToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);

    }
    @GetMapping("/products")
    public ResponseEntity<ProductResponseDto[]> getAllProductsDetails(){
        Product[] products= productService.getAllProducts();
        ProductResponseDto[] productResponseDtos=new ProductResponseDto[products.length];
        int index=0;
        for(Product product:products){
            productResponseDtos[index]=convertProductToProductResponseDto(product);
            index+=1;
        }
        return new ResponseEntity<>(productResponseDtos,HttpStatus.OK);
    }
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createNewProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product= productService.addProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
       ProductResponseDto productResponseDto= convertProductToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("id") int productId){
        Product product= productService.deleteAProduct(productId);
        ProductResponseDto productResponseDto= convertProductToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> putProduct(@PathVariable("id") int productId,@RequestBody ProductRequestDto productRequestDto){
        Product product= productService.putProduct(productId,productRequestDto);
        ProductResponseDto productResponseDto= convertProductToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> patchProduct(@PathVariable("id") int productId,@RequestBody ProductRequestDto productRequestDto){
        Product product=productService.patchProduct(productId,productRequestDto);
        ProductResponseDto productResponseDto= convertProductToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    private ProductResponseDto convertProductToProductResponseDto(Product product){
        String categoryTitle=product.getCategory().getTitle();
        ProductResponseDto productResponseDto=modelMapper.map(product,ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;

    }
}
