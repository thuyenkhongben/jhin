package com.code.jhin.controller;

import com.code.jhin.model.product.Product;
import com.code.jhin.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/list-product")
    public ResponseEntity<List<Product>> showListProduct() {
        List<Product> products = productService.findAllProduct();

        if (products.isEmpty()){
            return  new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
    }

}
