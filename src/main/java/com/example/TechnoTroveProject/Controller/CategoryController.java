package com.example.TechnoTroveProject.Controller;

import com.example.TechnoTroveProject.Entity.Category;
import com.example.TechnoTroveProject.Entity.Product;
import com.example.TechnoTroveProject.Service.CategoryService;
import com.example.TechnoTroveProject.Service.MobileService;
import com.example.TechnoTroveProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Integer categoryId) {
        return mobileService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/all")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping("/create")
    public ResponseEntity<List<Category>> getAllCategory(@RequestBody List<Category> category) {
        List<Category> saveCategory= categoryService.createCategory(category);
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }


}
