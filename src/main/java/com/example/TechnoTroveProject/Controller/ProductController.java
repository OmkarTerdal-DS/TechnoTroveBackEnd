package com.example.TechnoTroveProject.Controller;

import com.example.TechnoTroveProject.DTO.ProductDTO;
import com.example.TechnoTroveProject.DTO.ProductVariantDTO;
import com.example.TechnoTroveProject.Service.ProductService;
import com.example.TechnoTroveProject.Service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductVariantService productVariantService;

    // Fetch products by category ID with pagination
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(
            @PathVariable int categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> products = productService.getProductsByCategoryWithPagination(categoryId, pageable);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int productId) {
        ProductDTO product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Fetch all products with pagination
    @GetMapping("/all")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }


    // Search products by category ID or keyword
    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (categoryId != null) {
            Page<ProductDTO> products = productService.getProductsByCategoryWithPagination(categoryId, pageable);
            return ResponseEntity.ok(products);
        } else if (keyword != null) {
            Page<ProductDTO> products = productService.searchProductsByKeyword(keyword, pageable);
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.badRequest().body("Either categoryId or keyword must be provided.");
        }
    }

    // Fetch product variants by product ID
    @GetMapping("/product-variants/{productId}")
    public ResponseEntity<List<ProductVariantDTO>> getProductVariantsByProductId(@PathVariable int productId) {
        List<ProductVariantDTO> variants = productVariantService.getVariantsByProductId(productId);
        if (variants.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(variants);
    }
}
