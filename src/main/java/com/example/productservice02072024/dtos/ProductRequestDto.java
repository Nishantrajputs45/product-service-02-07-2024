package com.example.productservice02072024.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
