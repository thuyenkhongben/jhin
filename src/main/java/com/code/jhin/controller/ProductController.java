package com.code.jhin.controller;

import com.code.jhin.model.product.Picture;
import com.code.jhin.model.product.Product;
import com.code.jhin.service.productService.PictureService;
import com.code.jhin.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    PictureService pictureService;

    @GetMapping("/list-product")
    public ResponseEntity<List<Product>> showListProduct() {
        List<Product> products = productService.findAllProduct();

        if (products.isEmpty()){
            return  new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
    }

    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            productService.save(product);
            return new ResponseEntity<>(product,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> findByProductId(@PathVariable Long id){
        Optional<Product> product = productService.findByIdProduct(id);

        if (product.isPresent()){
            return new ResponseEntity<>(product.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id , @RequestBody Product product ) {
        Optional<Product> product1 = productService.findByIdProduct(id);

        if (product1.isPresent()){
            product1.get().setProductId(product.getProductId());
            product1.get().setCategory(product.getCategory());
            product1.get().setDescriptionProduct(product.getDescriptionProduct());
            product1.get().setNameProduct(product.getNameProduct());
            product1.get().setPictures(product.getPictures());
            product1.get().setPriceProduct(product.getPriceProduct());
            product1.get().setStatusProduct(product.isStatusProduct());

            productService.save(product1.get());

            return new ResponseEntity<Product>(product1.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
       Optional<Product>product = productService.findByIdProduct(id);

       if (product.isPresent()){
           List<Picture>pictures = pictureService.findByAllProductId(id);

           if (!pictures.isEmpty()){
               for (Picture picture : pictures){
                   pictureService.remove(picture.getPictureId());
               }
           }
           productService.remove(id);
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
