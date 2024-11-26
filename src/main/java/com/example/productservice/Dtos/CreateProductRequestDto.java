package com.example.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CreateProductRequestDto {
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
}
