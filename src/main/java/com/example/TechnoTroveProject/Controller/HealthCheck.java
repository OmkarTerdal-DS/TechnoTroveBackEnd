package com.example.TechnoTroveProject.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class HealthCheck {

    @GetMapping("/health")
    public String health() {
        return "Everything is Fine";
    }
}
