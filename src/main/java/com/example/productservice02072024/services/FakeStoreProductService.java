package com.example.productservice02072024.services;

import com.example.productservice02072024.advice.ProductNotFound;
import com.example.productservice02072024.dtos.FakeStoreDto;
import com.example.productservice02072024.dtos.ProductRequestDto;
import com.example.productservice02072024.dtos.ProductResponseDto;
import com.example.productservice02072024.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(int productId) {
        FakeStoreDto fakeStoreDto=restTemplate.getForObject("https://fakestoreapi.com/products/"+productId,
                FakeStoreDto.class);
        if(fakeStoreDto==null){
            throw new ProductNotFound("your id is not matching with database id");
        }

        return fakeStoreDto.toProduct();
    }

    @Override
    public Product[] getAllProducts() {
        FakeStoreDto[] fakeStoreDtos=restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreDto[].class);
        int fakeStoreSize=fakeStoreDtos.length;
        Product[] products=new Product[fakeStoreSize];
        int index=0;
        for(FakeStoreDto fakeStoreDto:fakeStoreDtos){
            products[index]=fakeStoreDto.toProduct();
            index+=1;
        }
       return products;
    }

    @Override
    public Product addProduct(String title, String description, String imageURL, String category, double price) {
        FakeStoreDto fakeStoreDto=new FakeStoreDto();
        fakeStoreDto.setTitle(title);
        fakeStoreDto.setDescription(description);
        fakeStoreDto.setImage(imageURL);
        fakeStoreDto.setCategory(category);
        fakeStoreDto.setPrice(price);

        FakeStoreDto response=restTemplate.postForObject("https://fakestoreapi.com/products/",fakeStoreDto,FakeStoreDto.class);
        return response.toProduct();


    }

    @Override
    public Product deleteAProduct(int productId) {
        ResponseEntity<FakeStoreDto> fakeStoreDto=restTemplate.exchange("https://fakestoreapi.com/products/"+productId, HttpMethod.DELETE,null,FakeStoreDto.class);
        return fakeStoreDto.getBody().toProduct();
    }

    @Override
    public Product putProduct(int productId, ProductRequestDto productRequestDto) {
        FakeStoreDto fakeStoreDto=new FakeStoreDto();
        fakeStoreDto.setTitle(productRequestDto.getTitle());
        fakeStoreDto.setDescription(productRequestDto.getDescription());
        fakeStoreDto.setImage(productRequestDto.getImage());
        fakeStoreDto.setCategory(productRequestDto.getCategory());
        fakeStoreDto.setPrice(productRequestDto.getPrice());

        HttpEntity<FakeStoreDto> requestUpdate = new HttpEntity<>(fakeStoreDto);
        ResponseEntity<FakeStoreDto> response=restTemplate.exchange("https://fakestoreapi.com/products/"+productId,HttpMethod.PUT,requestUpdate,FakeStoreDto.class);

        return response.getBody().toProduct();

    }

    @Override
    public Product patchProduct(int productId, ProductRequestDto productRequestDto) {
        FakeStoreDto fakeStoreDto=new FakeStoreDto();
        fakeStoreDto.setTitle(productRequestDto.getTitle());
        fakeStoreDto.setDescription(productRequestDto.getDescription());
        fakeStoreDto.setImage(productRequestDto.getImage());
        fakeStoreDto.setCategory(productRequestDto.getCategory());
        fakeStoreDto.setPrice(productRequestDto.getPrice());

        fakeStoreDto.setId(productId);
        return fakeStoreDto.toProduct();
    }
}
