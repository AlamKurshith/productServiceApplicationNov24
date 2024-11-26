package com.example.productservice.Controllers;

import com.example.productservice.Dtos.CreateProductRequestDto;
import com.example.productservice.Dtos.ErrorDto;
import com.example.productservice.Exceptions.ProductNotFoundException;
import com.example.productservice.Models.Product;
import com.example.productservice.ProductServiceApplication;
import com.example.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){

        List<Product> products = productService.getAllProducts();
        return products;

    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductDetails(id);
        return product;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto requestDto){
        Product product = productService.createProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getImage(),
                requestDto.getPrice(),
                requestDto.getCategory()
        );

        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatusCode.valueOf(201));

        return responseEntity;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        Long deletedProductId = productService.deleteProduct(id);
        return new ResponseEntity<>(deletedProductId, HttpStatus.OK);
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorDto> handleNPEException(){
//
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage("Something went wrong :( ");
//
//        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(
//                errorDto,
//                HttpStatusCode.valueOf(501));
//        return responseEntity;
//    }
//
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handlePNFException(){
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage("Product not found");
//        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto,
//                HttpStatusCode.valueOf(404));
//        return responseEntity;
//    }


}
