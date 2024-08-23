package com.example.productservice02072024.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product  {
    int id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    @ManyToOne
    private Category category;



}
