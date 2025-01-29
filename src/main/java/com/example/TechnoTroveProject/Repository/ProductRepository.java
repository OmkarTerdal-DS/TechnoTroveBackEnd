package com.example.TechnoTroveProject.Repository;

import com.example.TechnoTroveProject.DTO.ProductDTO;
import com.example.TechnoTroveProject.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Fetch paginated products by category ID and map to ProductDTO
    @Query("SELECT new com.example.TechnoTroveProject.DTO.ProductDTO(p.id, p.name, p.description, p.categoryId, p.imagePath) " +
            "FROM Product p WHERE p.categoryId = :categoryId")
    Page<ProductDTO> findProductDTOByCategoryId(@Param("categoryId") int categoryId, Pageable pageable);

    // Fetch paginated products by category ID
    Page<Product> findByCategoryId(int categoryId, Pageable pageable);

    // Search products by name (case-insensitive) with pagination
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> findByNameContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);

    // Fetch all products as ProductDTO with pagination
    @Query("SELECT new com.example.TechnoTroveProject.DTO.ProductDTO(p.id, p.name, p.description, p.categoryId, p.imagePath) " +
            "FROM Product p")
    Page<ProductDTO> findAllAsProductDTO(Pageable pageable);
}
