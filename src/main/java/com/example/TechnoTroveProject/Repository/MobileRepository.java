package com.example.TechnoTroveProject.Repository;

import com.example.TechnoTroveProject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MobileRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.categoryId = :categoryId")
    List<Product> findProductById(@Param("categoryId") Integer categoryId);
}
