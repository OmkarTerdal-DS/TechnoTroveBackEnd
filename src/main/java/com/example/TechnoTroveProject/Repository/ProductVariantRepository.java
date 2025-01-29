package com.example.TechnoTroveProject.Repository;

import com.example.TechnoTroveProject.DTO.ProductVariantDTO;
import com.example.TechnoTroveProject.Entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    @Query("SELECT new com.example.TechnoTroveProject.DTO.ProductVariantDTO(v.id, v.name, v.price, v.sku, v.imageUrl1, v.imageUrl2, v.imageUrl3) " +
            "FROM ProductVariant v WHERE v.product.id = :productId")
    List<ProductVariantDTO> findProductVariantsByProductId(@Param("productId") int productId);


    List<ProductVariant> findByNameContainingIgnoreCase(String query);
}
