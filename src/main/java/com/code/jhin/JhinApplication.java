package com.code.jhin;

import com.code.jhin.model.product.Picture;
import com.code.jhin.service.orderServiceImpl.OrderServiceImpl;
import com.code.jhin.service.orderServie.OrderService;
import com.code.jhin.service.productService.CategoryService;
import com.code.jhin.service.productService.PictureService;
import com.code.jhin.service.productService.ProductService;
import com.code.jhin.service.productServiceImpl.CategoryServiceImpl;
import com.code.jhin.service.productServiceImpl.PictureServiceImpl;
import com.code.jhin.service.productServiceImpl.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        JhinApplication.class,
        Jsr310JpaConverters.class
})
public class JhinApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(JhinApplication.class, args);
    }

    @Bean
    public ProductService productService() {
    return new ProductServiceImpl();
    }

    @Bean
    public CategoryService categoryService(){
        return new CategoryServiceImpl();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
    @Bean
    public PictureService pictureService() {
        return new PictureServiceImpl();
    }


}
