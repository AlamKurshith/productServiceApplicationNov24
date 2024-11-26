package com.example.productservice.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Product extends BaseModel{

    private String title;
    private String Description;
    private String imageUrl;
    private double price;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private int quantity;

}
