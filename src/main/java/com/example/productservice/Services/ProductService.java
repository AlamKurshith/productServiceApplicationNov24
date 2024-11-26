package com.example.productservice.Services;

import com.example.productservice.Exceptions.ProductNotFoundException;
import com.example.productservice.Models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductDetails(long id) throws ProductNotFoundException;
    public List<Product> getAllProducts();
    public Product createProduct(String title, String description, String image, double price, String category);
    public Long deleteProduct(long id) throws ProductNotFoundException;

}
