package com.code.jhin.model.product;

import com.code.jhin.model.username.User;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;


    @NaturalId
    @NotBlank
    @Column(unique = true)
    private String  categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product>products;


    public Category() {

    }
    public Category(  @NotBlank String categoryName) {

        this.categoryName = categoryName;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
