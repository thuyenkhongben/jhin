package com.code.jhin.model.product;

import com.code.jhin.model.order.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;


    private String nameProduct;


    private Double priceProduct;


    private Long amount;


    private String descriptionProduct;

//    @OneToMany(targetEntity = Picture.class  , cascade = CascadeType.ALL)
//    private String pictures;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Boolean statusProduct;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Product() {
    }
    public Product( String nameProduct, Double priceProduct , Long amount , String descriptionProduct ,
                   List<Picture> pictures , Category category, Boolean statusProduct , Order order){
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.amount = amount;
        this.descriptionProduct = descriptionProduct;
        this.pictures = pictures;
        this.category = category;
        this.statusProduct = statusProduct;
        this.order = order;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Boolean isStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(Boolean statusProduct) {
        this.statusProduct = statusProduct;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
