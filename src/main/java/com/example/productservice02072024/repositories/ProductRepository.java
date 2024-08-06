package com.example.productservice02072024.repositories;

import com.example.productservice02072024.models.Product;
import com.example.productservice02072024.repositories.projections.ProductProjection;
import com.example.productservice02072024.repositories.projections.ProductWithIdTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(Integer id);

    List<Product> findAllByCategory_Title(String title);
    List<Product> findByCategory_TitleContaining(String title);

    @Query("select p from Product p where p.category.title= :categoryName ")
    List<Product> getProductWithCategoryName(String categoryName);
    @Query("select p.title from Product p where p.category.title= :categoryName")
    List<String> someTitleMethod(String categoryName);

    @Query("select p.id as id, p.title as title from Product p where p.category.title= :categoryName")
    List<ProductWithIdTitle> someTitleAndIdMethod(String categoryName);

    @Query("select p.id as id, p.title as title,p.price as price from Product p where p.category.title= :categoryName")
    List<ProductProjection> someTitleAndIdWithPriceMethod(String categoryName);

    //Native SQL Query

    @Query(value= "select * from Product p where p.id= :id",nativeQuery = true)
    Product someNativeSqlQuery(Integer id);

}
