package com.code.jhin.service.productService;

import com.code.jhin.model.product.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategory();

    Optional<Category> findByIdCategory(Long id);

    void save(Category category);

    void remove( Long id);

}
