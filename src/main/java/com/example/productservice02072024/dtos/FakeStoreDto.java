package com.example.productservice02072024.dtos;

import com.example.productservice02072024.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public ProductResponseDto toProductResponseDto(){
        ProductResponseDto productResponseDto=new ProductResponseDto();
        productResponseDto.setId(id);
        productResponseDto.setTitle(title);
        productResponseDto.setDescription(description);
        productResponseDto.setPrice(price);
        productResponseDto.setImage(image);
        productResponseDto.setCategory(category);
        return productResponseDto;
    }
}
