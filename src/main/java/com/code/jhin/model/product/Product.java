package com.code.jhin.model.product;

import com.code.jhin.model.order.Order;
import com.code.jhin.model.product.Category;
import com.code.jhin.model.product.Picture;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotBlank
    private String nameProduct;

    @NotBlank
    private Double priceProduct;

    @NotBlank
    private String descriptionProduct;

    @OneToMany(targetEntity = Picture.class , mappedBy = "picture")
    private List<Picture>pictures;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean statusProduct;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Product() {
    }
    public Product(@NotBlank String nameProduct, @NotBlank Double priceProduct , @NotBlank String descriptionProduct ,
                   List<Picture>pictures , Category category, boolean statusProduct){
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.descriptionProduct = descriptionProduct;
        this.pictures = pictures;
        this.category = category;
        this.statusProduct = statusProduct;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(boolean statusProduct) {
        this.statusProduct = statusProduct;
    }
}
