package com.code.jhin.model.order;

import com.code.jhin.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long oderId;

    @NotBlank
    private  String nameOder;

    private Double totalProductPrice;

    private Date dateOder;

    private Date dateReceive;

    @NotBlank
    private String addressUser;


    @OneToMany(targetEntity = Product.class , mappedBy = "products")
    private List<Product> products;

    public Order() {
    }
//
//    public Order(@NotBlank String nameOder , Double totalProductPrice, Date dateOder , Date dateReceive ,
//                 String addressUser , List<Product> products) {
//        this.nameOder = nameOder;
//        this.totalProductPrice = totalProductPrice;
//        this.dateOder= dateOder;
//        this.dateReceive = dateReceive;
//        this.addressUser = addressUser;
//        this.products = products;
//    }

    public Long getOderId() {
        return oderId;
    }

    public void setOderId(Long oderId) {
        this.oderId = oderId;
    }

    public String getNameOder() {
        return nameOder;
    }

    public void setNameOder(String nameOder) {
        this.nameOder = nameOder;
    }

    public Double getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(Double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public Date getDateOder() {
        return dateOder;
    }

    public void setDateOder(Date dateOder) {
        this.dateOder = dateOder;
    }

    public Date getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(Date dateReceive) {
        this.dateReceive = dateReceive;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
