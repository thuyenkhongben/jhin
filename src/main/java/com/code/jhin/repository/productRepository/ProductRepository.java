package com.code.jhin.repository.productRepository;

import com.code.jhin.model.product.Product;
import com.sun.org.apache.bcel.internal.generic.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product , Long> {

//    @Query(value = "select products from category_products where category_category_id = categorId")
    List<Product>findAllByCategory(Long id);
}
