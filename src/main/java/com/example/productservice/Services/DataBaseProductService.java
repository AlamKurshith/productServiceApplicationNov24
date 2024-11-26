package com.example.productservice.Services;

import com.example.productservice.Exceptions.ProductNotFoundException;
import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import com.example.productservice.Repository.CategoryRepository;
import com.example.productservice.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("databaseProductService")
public class DataBaseProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public DataBaseProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductDetails(long id) throws ProductNotFoundException {
        Optional<Product> productOptionalFromDB =  productRepository.findById(id);
        if(productOptionalFromDB.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        Product productFromDB = productOptionalFromDB.get();
        return productFromDB;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll() ;
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String categoryName) {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category categoryFromDatabase = categoryRepository.findByName(categoryName);
        if(categoryFromDatabase == null){
            Category category = new Category();
            category.setName(categoryName);
            //categoryFromDatabase = categoryRepository.save(category);
        }
        product.setCategory(categoryFromDatabase);
        return productRepository.save(product);
    }

    @Override
    public Long deleteProduct(long id) throws ProductNotFoundException {
        return 0L;
    }


}
