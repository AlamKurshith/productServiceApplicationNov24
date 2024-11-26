package com.example.productservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Lazy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.mapping.Join;

import java.util.List;

@Getter
@Setter

@Entity

public class Category extends BaseModel{

    private String name;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE},
            fetch = FetchType.EAGER)

    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    private List<Product> products;



}
