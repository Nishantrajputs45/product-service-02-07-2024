package com.example.productservice02072024.repositories;

import com.example.productservice02072024.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category save(Category category);
    Category findByTitle(String Name);

}
