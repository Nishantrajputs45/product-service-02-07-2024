package com.example.productservice02072024;

import com.example.productservice02072024.models.Product;
import com.example.productservice02072024.repositories.CategoryRepository;
import com.example.productservice02072024.repositories.ProductRepository;
import com.example.productservice02072024.repositories.projections.ProductProjection;
import com.example.productservice02072024.repositories.projections.ProductWithIdTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductService02072024ApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }
    @Test
    void testJpaDeclaredJoin(){
        List<Product> products=productRepository.findAllByCategory_Title("electronics");
        for(Product product:products){
            System.out.println(product.getTitle());
        }
    }

    @Test
    void testHQL(){
        List<Product> products=productRepository.getProductWithCategoryName("electronics");
        for(Product product:products){
            System.out.println(product.getTitle());
        }
    }
   @Test
    void testSpecificFields(){
        List<String> products_title=productRepository.someTitleMethod("new electronics");
       for(String title:products_title){
           System.out.println(title);
       }
   }
   @Test
    void testProjections(){
        List<ProductWithIdTitle> products=productRepository.someTitleAndIdMethod("new electronics");
        for(ProductWithIdTitle productWithIdTitle:products){
            System.out.println(productWithIdTitle.getTitle());
            System.out.println(productWithIdTitle.getId());
        }
   }
   @Test
    void testProjections1(){
       List<ProductProjection> products=productRepository.someTitleAndIdWithPriceMethod("new electronics");
       for(ProductProjection productWithIdTitle:products){
           System.out.println(productWithIdTitle.getTitle());
           System.out.println(productWithIdTitle.getId());
           System.out.println(productWithIdTitle.getPrice());

       }
   }
  @Test
    void testNativeQuery(){
        Product product=productRepository.someNativeSqlQuery(1);
      System.out.println(product.getTitle());
  }
}
