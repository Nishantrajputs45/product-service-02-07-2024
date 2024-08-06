package com.example.productservice02072024.repositories.projections;

import com.example.productservice02072024.models.Category;

public interface ProductProjection {

    Integer getId();
    String getTitle();
    String getDescription();

    Double getPrice();
    Category getCategory();
    String getImageUrl();
}
