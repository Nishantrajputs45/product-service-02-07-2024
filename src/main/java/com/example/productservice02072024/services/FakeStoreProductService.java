package com.example.productservice02072024.services;

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
    public ProductResponseDto getSingleProduct(int productId) {
        FakeStoreDto fakeStoreDto=restTemplate.getForObject("https://fakestoreapi.com/products/"+productId,
                FakeStoreDto.class);

        return fakeStoreDto.toProductResponseDto();
    }

    @Override
    public ProductResponseDto[] getAllProducts() {
        FakeStoreDto[] fakeStoreDtos=restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreDto[].class);
        int fakeStoreSize=fakeStoreDtos.length;
        ProductResponseDto[] productResponseDtos=new ProductResponseDto[fakeStoreSize];
        int index=0;
        for(FakeStoreDto fakeStoreDto:fakeStoreDtos){
            productResponseDtos[index]=fakeStoreDto.toProductResponseDto();
            index+=1;
        }
       return productResponseDtos;
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
        return response.toProductResponseDto();


    }

    @Override
    public ProductResponseDto deleteAProduct(int productId) {
        ResponseEntity<FakeStoreDto> fakeStoreDto=restTemplate.exchange("https://fakestoreapi.com/products/"+productId, HttpMethod.DELETE,null,FakeStoreDto.class);
        return fakeStoreDto.getBody().toProductResponseDto();
    }

    @Override
    public ProductResponseDto putProduct(int productId, ProductRequestDto productRequestDto) {
        FakeStoreDto fakeStoreDto=new FakeStoreDto();
        fakeStoreDto.setTitle(productRequestDto.getTitle());
        fakeStoreDto.setDescription(productRequestDto.getDescription());
        fakeStoreDto.setImage(productRequestDto.getImage());
        fakeStoreDto.setCategory(productRequestDto.getCategory());
        fakeStoreDto.setPrice(productRequestDto.getPrice());

        HttpEntity<FakeStoreDto> requestUpdate = new HttpEntity<>(fakeStoreDto);
        ResponseEntity<FakeStoreDto> response=restTemplate.exchange("https://fakestoreapi.com/products/"+productId,HttpMethod.PUT,requestUpdate,FakeStoreDto.class);

        return response.getBody().toProductResponseDto();

    }

    @Override
    public ProductResponseDto patchProduct(int productId, ProductRequestDto productRequestDto) {
        FakeStoreDto fakeStoreDto=new FakeStoreDto();
        fakeStoreDto.setTitle(productRequestDto.getTitle());
        fakeStoreDto.setDescription(productRequestDto.getDescription());
        fakeStoreDto.setImage(productRequestDto.getImage());
        fakeStoreDto.setCategory(productRequestDto.getCategory());
        fakeStoreDto.setPrice(productRequestDto.getPrice());

        fakeStoreDto.setId(productId);
        return fakeStoreDto.toProductResponseDto();
    }
}
