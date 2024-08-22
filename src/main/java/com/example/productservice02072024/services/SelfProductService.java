package com.example.productservice02072024.services;

import com.example.productservice02072024.advice.ProductNotFound;
import com.example.productservice02072024.dtos.ProductRequestDto;
import com.example.productservice02072024.models.Category;
import com.example.productservice02072024.models.Product;
import com.example.productservice02072024.repositories.CategoryRepository;
import com.example.productservice02072024.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product getSingleProduct(int productId) {
        Product product= productRepository.findByIdIs(productId);
        if(product==null){
            throw  new ProductNotFound(
                    "product with id "+productId+" not found."
            );
        }
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageSize,int pageNumber,String fieldName) {
        Page<Product> allProduct=productRepository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by(fieldName).ascending()));
        return allProduct;
    }

    @Override
    public Product addProduct(String title, String description, String imageURL, String category, double price) {
        Product newProduct=new Product();
        newProduct.setTitle(title);
        newProduct.setDescription(description);
        newProduct.setImageUrl(imageURL);
        newProduct.setPrice(price);

        Category categoryFromDB=categoryRepository.findByTitle(category);
        if(categoryFromDB==null){
            Category newCategory=new Category();
            newCategory.setTitle(category);
           // categoryRepository.save(newCategory);
            categoryFromDB=newCategory;
        }
        newProduct.setCategory(categoryFromDB);

        return productRepository.save(newProduct);
    }

    @Override
    public Product deleteAProduct(int productId) {
        Product product= productRepository.findByIdIs(productId);
        if(product==null){
            throw  new ProductNotFound(
                    "product with id "+productId+" not found."
            );
        }
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product putProduct(int productId, String title, String description, String imageUrl, String category, double price) {
        Product productInDb=productRepository.findByIdIs(productId);
        if(productInDb==null){
            throw  new ProductNotFound("Product with "+productId+" not in db "+" so you can not update");

        }
        productInDb.setTitle(title);
        productInDb.setDescription(description);
        productInDb.setImageUrl(imageUrl);
        productInDb.setPrice(price);
        Category categoryFromDb=categoryRepository.findByTitle(category);
        if(categoryFromDb==null){
            Category newCategory=new Category();
            newCategory.setTitle(category);
            categoryFromDb=newCategory;
        }
        productInDb.setCategory(categoryFromDb);

        return productRepository.save(productInDb);
    }

    @Override
    public Product patchProduct(int productId, String title, String description, String imageUrl, String category, double price) {
        Product productInDb=productRepository.findByIdIs(productId);
        if(productInDb==null){
            throw  new ProductNotFound("Product with "+productId+" not in db "+" so you can not update");

        }
        if(title!=null){
            productInDb.setTitle(title);
        }
        if(description!=null){
            productInDb.setDescription(description);
        }
        if(imageUrl!=null){
            productInDb.setImageUrl(imageUrl);
        }
        if(price!=0){
            productInDb.setPrice(price);
        }
        if(category!=null){
            Category categoryFromDb=categoryRepository.findByTitle(category);
            if(categoryFromDb==null){
                Category newCategory=new Category();
                newCategory.setTitle(category);
                categoryFromDb=newCategory;
            }
            productInDb.setCategory(categoryFromDb);
        }
        return productRepository.save(productInDb);
    }

}
