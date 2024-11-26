package com.example.productservice.Repository;

import com.example.productservice.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
    Category save(Category category);
    Optional<Category> findById(Long Id);

}
