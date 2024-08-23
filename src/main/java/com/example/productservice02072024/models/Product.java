package com.example.productservice02072024.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class Product implements Serializable {
    int id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;



}
