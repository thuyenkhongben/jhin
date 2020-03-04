package com.code.jhin.controller;

import com.code.jhin.model.product.Category;
import com.code.jhin.model.product.Product;
import com.code.jhin.service.productService.CategoryService;
import com.code.jhin.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/list-category")
    public ResponseEntity<List<Category>> allCategory () {
        List<Category> categories = categoryService.findAllCategory();

        if (categories.isEmpty()){
            return  new  ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }

        return  new ResponseEntity<List<Category>>(categories , HttpStatus.OK);
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory (@RequestBody Category category ) {
        try {
            categoryService.save(category);
            return new ResponseEntity<>( category ,HttpStatus.CREATED);

        }catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> findByIdCategory (@PathVariable Long id){
        Optional<Category> category = categoryService.findByIdCategory(id);

        if (category.isPresent()){
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
        Optional<Category> category1 = categoryService.findByIdCategory(id);
        if (category1.isPresent()){
            category1.get().setCategoryName(category.getCategoryName());
            category1.get().setCategoryId(category.getCategoryId());

            categoryService.save(category1.get());

            return new ResponseEntity<Category>(category1.get() , HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory( @PathVariable Long id){
        Optional<Category>category = categoryService.findByIdCategory(id);

        if (category.isPresent()){
            List<Product>products = productService.findAllByCategoryId(id);

            if (!products.isEmpty()){
                for (Product product : products){
                    productService.remove(product.getProductId());
                }
            }
            categoryService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
