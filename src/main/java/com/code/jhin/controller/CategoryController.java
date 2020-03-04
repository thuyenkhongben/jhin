package com.code.jhin.controller;

import com.code.jhin.model.product.Category;
import com.code.jhin.service.productService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

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

}
