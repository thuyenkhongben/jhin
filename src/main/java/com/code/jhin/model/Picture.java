package com.code.jhin.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pictureId;

    @NotBlank
    private String pictureName;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Picture() {
    }

    public Picture(@NotBlank String pictureName) {
        this.pictureName = pictureName;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
