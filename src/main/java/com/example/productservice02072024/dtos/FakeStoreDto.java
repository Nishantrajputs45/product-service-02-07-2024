package com.example.productservice02072024.dtos;

import com.example.productservice02072024.models.Category;
import com.example.productservice02072024.models.Product;
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

    public Product toProduct(){
        Product product=new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category categ=new Category();
        categ.setTitle(category);
        product.setCategory(categ);
        return product;
    }
}
