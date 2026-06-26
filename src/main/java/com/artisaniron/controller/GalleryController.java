package com.artisaniron.controller;

import com.artisaniron.exception.ProductNotFoundException;
import com.artisaniron.model.Product;
import com.artisaniron.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
    private final ProductService productService;

    public GalleryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String gallery(@RequestParam(required = false) String category, Model model) {
        List<Product> products;
        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", productService.getCategories());
        return "gallery";
    }

    @GetMapping("/{slug}")
    public String productDetail(@PathVariable String slug, Model model) {
        Product product = productService.getProductBySlug(slug)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + slug));

        model.addAttribute("product", product);
        return "product-detail";
    }
}
