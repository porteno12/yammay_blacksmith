package com.artisaniron.controller;

import com.artisaniron.service.ProductService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Stands down for DemoHomeController when the demo profile sets firebase.enabled=false.
// matchIfMissing keeps this the default in every other configuration.
@Controller
@ConditionalOnProperty(name = "firebase.enabled", havingValue = "true", matchIfMissing = true)
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featuredProducts", productService.getFeaturedProducts());
        return "index";
    }
}
