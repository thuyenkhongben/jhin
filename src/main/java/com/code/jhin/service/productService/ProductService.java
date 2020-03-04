package com.code.jhin.service.productService;

import com.code.jhin.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product>findAllByCategoryId(Long id);

    List<Product> findAllProduct();

    Optional<Product>  findByIdProduct(Long id);

    void save(Product product);

    void remove(Long id);
}
