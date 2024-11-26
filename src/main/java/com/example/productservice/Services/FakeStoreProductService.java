package com.example.productservice.Services;

import com.example.productservice.Dtos.FakeStoreCreateProductDto;
import com.example.productservice.Dtos.FakeStoreProductDto;
import com.example.productservice.Exceptions.ProductNotFoundException;
import com.example.productservice.Models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductDetails(long id) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if(responseEntity.getStatusCode() == HttpStatusCode.valueOf(404)){
            //show error
        }
        else if(responseEntity.getStatusCode() == HttpStatusCode.valueOf(500)){
            //show error
        }

        FakeStoreProductDto responseBody = responseEntity.getBody();
        if(responseBody == null){
            throw new ProductNotFoundException("Product not found");

        }
        return responseEntity.getBody().toProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto responseDto [] = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto dto : responseDto ){
            products.add(dto.toProduct());
        }
        return products;
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String category) {

        FakeStoreCreateProductDto requestDto = new FakeStoreCreateProductDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setImage(image);
        requestDto.setPrice(price);
        requestDto.setCategory(category);

        FakeStoreProductDto responseDto = restTemplate.postForObject("https://fakestoreapi.com/products",
                requestDto,
                FakeStoreProductDto.class);

        return responseDto.toProduct();
    }

    @Override
    public Long deleteProduct(long id) throws ProductNotFoundException{

        String url = "https://fakestoreapi.com/products/{id}";
        try{
            ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    null,
                    FakeStoreProductDto.class,
                    id
            );

            FakeStoreProductDto deletedProduct = responseEntity.getBody();
            if(deletedProduct != null){
                return deletedProduct.getId();

            }
            else{
                throw new ProductNotFoundException("Product not found");
            }

        }
        catch (Exception e){

            throw new RuntimeException(" An error occurred while deleting the product with ID: ", e);

        }


    }


}
