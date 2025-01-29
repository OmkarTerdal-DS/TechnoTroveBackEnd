package com.example.TechnoTroveProject.Service;

import com.example.TechnoTroveProject.Entity.Category;
import com.example.TechnoTroveProject.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class CategoryService {

    @Autowired
    private   CategoryRepository categoryRepository;

//    public List<Category> getAllCategories(){
//        return categoryRepository.findAll();
//    }
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

//    public List<Category> createCategory(List<Category> category){
//        return categoryRepository.saveAll(category);
//    }

    @Cacheable(value = "categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> createCategory(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

}
