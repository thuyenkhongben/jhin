package com.code.jhin.repository.productRepository;

import com.code.jhin.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
