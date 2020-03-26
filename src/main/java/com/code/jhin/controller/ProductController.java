package com.code.jhin.controller;

import com.code.jhin.model.product.Picture;
import com.code.jhin.model.product.Product;
import com.code.jhin.playLoad.response.ApiResponse;
import com.code.jhin.service.productService.PictureService;
import com.code.jhin.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    PictureService pictureService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> showListProduct() {
        List<Product> products = productService.findAllProduct();

        if (products.isEmpty()){
            return  new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product product ) {
        try {
            productService.saveAndFlush(product);
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse(true , "Create successfully " , product),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<ApiResponse>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> findByProductId(@PathVariable Long id){
        Optional<Product> product = productService.findByIdProduct(id);

        if (product.isPresent()){
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse(true,"successfilly", product),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id , @RequestBody Product product ) {
        Optional<Product> product1 = productService.findByIdProduct(id);

        if (product1.isPresent() ){
            product1.get().setCategory(product.getCategory());
            product1.get().setDescriptionProduct(product.getDescriptionProduct());
            product1.get().setNameProduct(product.getNameProduct());
            product1.get().setPictures(product.getPictures());
            product1.get().setPriceProduct(product.getPriceProduct());
            product1.get().setStatusProduct(product.isStatusProduct());

            productService.saveAndFlush(product1.get());

            return new ResponseEntity<ApiResponse> (
                    new ApiResponse(true , "successfully" , product1), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponse>(
                new ApiResponse(false , "loi" , null),HttpStatus.BAD_REQUEST);
    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
       Optional<Product>product = productService.findByIdProduct(id);

       if (product.isPresent()) {
            pictureService.findByAllProductId(id);
           productService.remove(id);
       }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
