package com.example.TechnoTroveProject.Service;

import com.example.TechnoTroveProject.DTO.ProductDTO;
import com.example.TechnoTroveProject.Entity.Product;
import com.example.TechnoTroveProject.Repository.CategoryRepository;
import com.example.TechnoTroveProject.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

//    // Fetch products by category with pagination
//    public Page<ProductDTO> getProductsByCategoryWithPagination(int categoryId, Pageable pageable) {
//        return productRepository.findByCategoryId(categoryId, pageable)
//                .map(this::convertToDTO);
//    }
//
//    public ProductDTO getProductById(int productId) {
//        return productRepository.findById(productId)
//                .map(product -> new ProductDTO(
//                        product.getId(),
//                        product.getName(),
//                        product.getDescription(),
//                        product.getCategoryId(),
//                        product.getImagePath()
//                ))
//                .orElse(null);
//    }
//    // Fetch all products with pagination
//    public Page<ProductDTO> getAllProducts(Pageable pageable) {
//        return productRepository.findAll(pageable)
//                .map(this::convertToDTO);
//    }
//
//    // Search products by keyword with pagination
//    public Page<ProductDTO> searchProductsByKeyword(String keyword, Pageable pageable) {
//        return productRepository.findByNameContainingIgnoreCase(keyword, pageable)
//                .map(this::convertToDTO);
//    }
//
//    // Convert Product to ProductDTO
//    private ProductDTO convertToDTO(Product product) {
//        return new ProductDTO(
//                product.getId(),
//                product.getName(),
//                product.getDescription(),
//                product.getCategoryId(),
//                product.getImagePath()
//        );
//    }



    @Cacheable(value = "productsByCategory", key = "#categoryId + '-' + #pageable.pageNumber")
    public Page<ProductDTO> getProductsByCategoryWithPagination(int categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable)
                .map(this::convertToDTO);
    }

    @Cacheable(value = "productDetails", key = "#productId")
    public ProductDTO getProductById(int productId) {
        return productRepository.findById(productId)
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getCategoryId(),
                        product.getImagePath()
                ))
                .orElse(null);
    }

    // Fetch all products with pagination
    @Cacheable(value = "allProducts", key = "#pageable.pageNumber")
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    // Search products by keyword with pagination
    @Cacheable(value = "searchProducts", key = "#keyword + '-' + #pageable.pageNumber")
    public Page<ProductDTO> searchProductsByKeyword(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(this::convertToDTO);
    }

    // Convert Product to ProductDTO
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategoryId(),
                product.getImagePath()
        );
    }
}
