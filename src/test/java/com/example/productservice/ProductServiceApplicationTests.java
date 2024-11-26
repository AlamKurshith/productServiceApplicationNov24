package com.example.productservice;

import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import com.example.productservice.Repository.CategoryRepository;
import com.example.productservice.Repository.ProductRepository;
import com.example.productservice.Repository.Projections.ProductTitleAndDesc;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        Optional<Product> product = productRepository
                .findByTitleAndCategory_name("iPhone", "phones");

        System.out.println(product.get().getTitle());
    }

    @Test
    public void test2(){
        List<Product> productOptional = productRepository
                .getProductData("phones");

        System.out.println(productOptional.get(0).getTitle());
    }

    @Test
    public void test3(){
        Product productOptional = productRepository.getProductData2(1L);

        System.out.println(productOptional.getTitle());
    }

    @Test
    public void test4(){
        ProductTitleAndDesc productOptional = productRepository.getProductData3(1L);

        System.out.println(productOptional.getTitle() + ", " + productOptional.getDescription());
    }

    @Test
    @Transactional
    public void testFetchType(){
        Optional<Category> category = categoryRepository.findById(1L);
        System.out.println(category.get().getName());
        System.out.println(category.get().getProducts());

    }

    @Test
    @Transactional
    public void NPlusOneProblem(){
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories){
            for(Product product : category.getProducts()){
                System.out.println("------>>>" + product.getTitle());
            }
        }

    }

    @Test
    @Transactional
    public void NPlusOneProblemHomeWork1(){

        //With Fetch type = Eager, fetch mode is SELECT and fetching the product
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories){
            for(Product product : category.getProducts()){
                System.out.println("------>>>" + product.getTitle());
            }
        }

        // Firing 5 queries at once

    }

    @Test
    @Transactional
    public void NPlusOneProblemHomeWork2(){
        //With Fetch type = Eager, fetch mode is SELECT and not fetching the product
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories){
            System.out.println("--->> " + category.getName() + " <<---");
        }
        // Firing 5 queries at once
    }

    @Test
    @Transactional
    public void NPlusOneProblemHomeWork3(){
        //With Fetch type = Eager, fetch mode is SUBSELECT and fetching the product
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories){
            for(Product product : category.getProducts()){
                System.out.println("------>>>" + product.getTitle());
            }
        }
        // Firing only 2 queries
    }

    @Test
    @Transactional
    public void NPlusOneProblemHomeWork4(){
        //With Fetch type = Eager, fetch mode is SUBSELECT and  not fetching the product
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories){
            System.out.println("--->> " + category.getName() + " <<---");
        }
        // Firing only 2 queries
    }

    @Test
    @Transactional
    public void NPlusOneProblemHomeWork5(){
        //With Fetch type = Eager, fetch mode is JOIN and fetching the product
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories){
            for(Product product : category.getProducts()){
                System.out.println("------>>>" + product.getTitle());
            }
        }
        // Firing 5 queries
    }

    @Test
    @Transactional
    public void NPlusOneProblemHomeWork6(){
        //With Fetch type = Eager, fetch mode is JOIN and not fetching the product
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories){
            System.out.println("--->> " + category.getName() + " <<---");
        }
        // Firing 5 queries
    }








}
