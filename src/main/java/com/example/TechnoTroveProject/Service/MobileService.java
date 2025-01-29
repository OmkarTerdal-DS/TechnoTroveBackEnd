package com.example.TechnoTroveProject.Service;


import com.example.TechnoTroveProject.Entity.Product;
import com.example.TechnoTroveProject.Repository.MobileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Service
public class MobileService {
    @Autowired
    private MobileRepository mobileRepository;

    public List<Product> getProductsByCategoryId(Integer categoryId){
        return mobileRepository.findProductById(categoryId);
    }

}
