package com.example.productservice02072024.repositories;

import com.example.productservice02072024.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(Integer id);

}
