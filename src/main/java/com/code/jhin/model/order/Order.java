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
    private Long oderId;

    private String totalProductPrice;

    private String dateOder;

    private String dateReceive;

    @NotBlank
    private String addressUser;

    @NotBlank
    private String nameReceiver;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_oder" , joinColumns = @JoinColumn(name = "orderId") ,
    inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> product = new ArrayList<Product>();

//    @ManyToOne
//    @JoinColumn
//    private User user;

    public Order() {
    }
////  this.product = product;
//
    public Order( String totalProductPrice, String dateOder,
                 String dateReceive,List<Product> product , @NotBlank String addressUser,
                  @NotBlank String nameReceiver ) {
        this.totalProductPrice = totalProductPrice;
        this.dateOder = dateOder;
        this.dateReceive = dateReceive;
        this.addressUser = addressUser;
        this.product = product;
        this.nameReceiver = nameReceiver;
    }

    public Long getOderId() {
        return oderId;
    }

    public void setOderId(Long oderId) {
        this.oderId = oderId;
    }

    public String getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(String totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public String getDateOder() {
        return dateOder;
    }

    public void setDateOder(String dateOder) {
        this.dateOder = dateOder;
    }

    public String getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(String dateReceive) {
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
