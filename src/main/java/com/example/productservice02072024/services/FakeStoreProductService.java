package com.example.productservice02072024.services;

import com.example.productservice02072024.dtos.FakeStoreDto;
import com.example.productservice02072024.dtos.ProductResponseDto;
import com.example.productservice02072024.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductResponseDto getSingleProduct(int productId) {
        FakeStoreDto fakeStoreDto=restTemplate.getForObject("https://fakestoreapi.com/products/"+productId,
                FakeStoreDto.class);

        return fakeStoreDto.toProductResponseDto();
    }

    @Override
    public ProductResponseDto addProduct(String title, String description, String imageURL, String category, double price) {
        FakeStoreDto fakeStoreDto=new FakeStoreDto();
        fakeStoreDto.setTitle(title);
        fakeStoreDto.setDescription(description);
        fakeStoreDto.setImage(imageURL);
        fakeStoreDto.setCategory(category);
        fakeStoreDto.setPrice(price);

        FakeStoreDto response=restTemplate.postForObject("https://fakestoreapi.com/products/",fakeStoreDto,FakeStoreDto.class);
        return fakeStoreDto.toProductResponseDto();


    }
}
