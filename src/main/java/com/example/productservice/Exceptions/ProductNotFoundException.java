package com.example.productservice.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Executors;



public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message){
        super(message);
    }

}
