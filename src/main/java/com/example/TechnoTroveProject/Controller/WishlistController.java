package com.example.TechnoTroveProject.Controller;

import com.example.TechnoTroveProject.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<String> addToWishlist(@RequestBody Map<String, Integer> request) {
        Integer variantId = request.get("variantId");
        if (variantId == null || variantId <= 0) {
            return ResponseEntity.badRequest().body("Invalid Variant ID");
        }

        boolean isAdded = wishlistService.addToWishlist(variantId);
        if (isAdded) {
            return ResponseEntity.ok("Variant added to wishlist");
        } else {
            return ResponseEntity.badRequest().body("Variant already in wishlist");
        }
    }

}
