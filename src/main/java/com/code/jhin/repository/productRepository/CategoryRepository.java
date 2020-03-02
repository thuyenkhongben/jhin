package com.code.jhin.repository.productRepository;

import com.code.jhin.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Long> {
}
