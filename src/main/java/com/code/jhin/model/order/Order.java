package com.code.jhin.model.order;

import com.code.jhin.model.product.Product;
import com.code.jhin.model.username.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Double totalProductPrice;

    private Date dateOder;

    private Date dateReceive;

    @NotBlank
    private String addressUser;

    @NotBlank
    private String nameReceiver;


    @ManyToMany
    @JoinTable(name = "product_order" , joinColumns = @JoinColumn(name = "orderId") ,
    inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> product = new ArrayList<Product>();

    public Order() {
    }
    public Order( Double totalProductPrice, Date dateOder,
                 Date dateReceive,List<Product> product , @NotBlank String addressUser,
                  @NotBlank String nameReceiver ) {
        this.totalProductPrice = totalProductPrice;
        this.dateOder = dateOder;
        this.dateReceive = dateReceive;
        this.addressUser = addressUser;
        this.product = product;
        this.nameReceiver = nameReceiver;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long oderId) {
        this.orderId = oderId;
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

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
