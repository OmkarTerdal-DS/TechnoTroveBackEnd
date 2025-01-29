package com.example.TechnoTroveProject.Controller;

import com.example.TechnoTroveProject.DTO.ProductVariantDTO;
import com.example.TechnoTroveProject.Entity.ProductVariant;
import com.example.TechnoTroveProject.Service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product-variant")
public class ProductVariantController {

    @Autowired
    private ProductVariantService productVariantService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductVariantDTO>> getProductVariantsByProductId(@PathVariable int productId) {
        List<ProductVariantDTO> variants = productVariantService.getVariantsByProductId(productId);

        if (variants.isEmpty()) {
            return ResponseEntity.notFound().build();  // Return 404 if no variants are found
        }
        return ResponseEntity.ok(variants);  // Return the variants as a List<ProductVariantDTO>
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<ProductVariant>> addProductVariant(@RequestBody List<ProductVariant> productVariant) {
        List<ProductVariant> savedProductVariant=productVariantService.addProductVariant(productVariant);
        return new ResponseEntity<>(savedProductVariant, HttpStatus.CREATED);
    }

}
