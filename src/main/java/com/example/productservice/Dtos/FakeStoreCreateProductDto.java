package com.example.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductDto {
    String title;
    String description;
    String image;
    double price;
    String category;
}
