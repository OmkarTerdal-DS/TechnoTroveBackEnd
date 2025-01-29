package com.example.TechnoTroveProject.Service;

import com.example.TechnoTroveProject.Entity.Wishlist;
import com.example.TechnoTroveProject.Repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

//    public boolean addToWishlist(int productVariantId) {
//        // Check if the specific variant already exists in the wishlist
//        if (wishlistRepository.existsByProductVariantId(productVariantId)) {
//            return false; // This variant is already in the wishlist
//        }
//
//        // Add the new variant to the wishlist
//        Wishlist wishlist = new Wishlist();
//        wishlist.setProductVariantId(productVariantId);
//        wishlistRepository.save(wishlist);
//        return true;
//    }


    public boolean addToWishlist(int productVariantId) {
        if (wishlistRepository.existsByProductVariantId(productVariantId)) {
            return false;
        }
        Wishlist wishlist = new Wishlist();
        wishlist.setProductVariantId(productVariantId);
        wishlistRepository.save(wishlist);
        return true;
    }

//    @Cacheable(value = "wishlist")
//    public List<Wishlist> getAllWishlistItems() {
//        return wishlistRepository.findAll();
//    }
}

