package com.example.productservice.Advices;

import com.example.productservice.Dtos.ErrorDto;
import com.example.productservice.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNPEException(){

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Something went wrong :( ");

        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(
                errorDto,
                HttpStatusCode.valueOf(501));
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handlePNFException(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Product not found");
        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto,
                HttpStatusCode.valueOf(404));
        return responseEntity;
    }
}
