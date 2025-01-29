package com.example.TechnoTroveProject.Service;

import com.example.TechnoTroveProject.DTO.ProductVariantDTO;
import com.example.TechnoTroveProject.Entity.ProductVariant;
import com.example.TechnoTroveProject.Repository.ProductVariantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ProductVariantService {

    @Autowired
    private ProductVariantRepository productVariantRepository;

//    public List<ProductVariantDTO> getVariantsByProductId(int productId) {
//        return productVariantRepository.findProductVariantsByProductId(productId);
//    }
//
//
//    public List<ProductVariant> addProductVariant(List<ProductVariant> productVariant) {
//        return productVariantRepository.saveAll(productVariant);
//    }
//    public List<ProductVariant> searchVariants(String query) {
//        return productVariantRepository.findByNameContainingIgnoreCase(query);
//    }


    @Cacheable(value = "productVariants", key = "#productId")
    public List<ProductVariantDTO> getVariantsByProductId(int productId) {
        return productVariantRepository.findProductVariantsByProductId(productId);
    }

    public List<ProductVariant> addProductVariant(List<ProductVariant> productVariants) {
        return productVariantRepository.saveAll(productVariants);
    }

    public List<ProductVariant> searchVariants(String query) {
        return productVariantRepository.findByNameContainingIgnoreCase(query);
    }
}
