package com.artisaniron.controller.admin;

import com.artisaniron.model.Product;
import com.artisaniron.service.ImageStorageService;
import com.artisaniron.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/products")
@PreAuthorize("hasRole('ADMIN')")
public class AdminProductController {
    private final ProductService productService;
    private final ImageStorageService imageStorageService;

    public AdminProductController(ProductService productService, ImageStorageService imageStorageService) {
        this.productService = productService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", productService.getCategories());
        return "admin/products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getCategories());
        return "admin/product-form";
    }

    @PostMapping
    public String saveProduct(
            @RequestParam String slug,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String materials,
            @RequestParam double price,
            @RequestParam String categorySlug,
            @RequestParam(required = false) boolean featured,
            @RequestParam(required = false) int sortOrder,
            @RequestParam(required = false) MultipartFile image,
            Model model) throws IOException {

        Product product = new Product();
        product.setSlug(slug);
        product.setName(name);
        product.setDescription(description);
        product.setMaterials(materials);
        product.setPrice(price);
        product.setCategorySlug(categorySlug);
        product.setFeatured(featured);
        product.setSortOrder(sortOrder);

        if (image != null && !image.isEmpty()) {
            String base64Image = imageStorageService.toBase64(image);
            product.setImageBase64(base64Image);
        }

        productService.saveProduct(product);
        model.addAttribute("successMessage", "המוצר נשמר בהצלחה!");

        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
