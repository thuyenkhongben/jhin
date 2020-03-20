package com.code.jhin.service.productServiceImpl;

import com.code.jhin.model.product.Product;
import com.code.jhin.repository.productRepository.PictureRepository;
import com.code.jhin.repository.productRepository.ProductRepository;
import com.code.jhin.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAllByCategoryId(Long id) {
        return productRepository.findAllByCategory(id);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findByIdProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void saveAndFlush(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }
}
