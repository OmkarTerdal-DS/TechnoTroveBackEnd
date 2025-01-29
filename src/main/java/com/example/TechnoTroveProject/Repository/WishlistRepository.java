package com.example.TechnoTroveProject.Repository;

import com.example.TechnoTroveProject.Entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    boolean existsByProductVariantId(int product_variant_Id); // Ensure this matches the field name in the entity
}