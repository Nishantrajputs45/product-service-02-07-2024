package com.example.productservice02072024.repositories;

import com.example.productservice02072024.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
