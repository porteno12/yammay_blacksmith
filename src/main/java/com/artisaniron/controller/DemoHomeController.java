package com.artisaniron.controller;

import com.artisaniron.model.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@ConditionalOnProperty(name = "firebase.enabled", havingValue = "false")
public class DemoHomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featuredProducts", getDemoProducts());
        return "demo-home";
    }

    private List<Product> getDemoProducts() {
        List<Product> products = new ArrayList<>();

        products.add(createDemoProduct(
            "1", "ornate-gate-black", "שער כניסה מעוצב", "gates",
            "שער ברזל שחור מעוצב בדוגמות מסורתיות עם טיפולי זהב",
            "ברזל חור, זהב שחור", 4500.00
        ));

        products.add(createDemoProduct(
            "2", "spiral-railing-gold", "מעקה ספירלי זהב", "railings",
            "מעקה ברזל משולב בעיצוב ספירלי עם דגש זהוב",
            "ברזל טוי, זהב אמיתי", 3200.00
        ));

        products.add(createDemoProduct(
            "3", "garden-arch", "קשת גן עתיקה", "decor",
            "קשת ברזל לתחתוני גן בעיצוב אירופאי קלאסי",
            "ברזל מכופף, חציבה ידנית", 2800.00
        ));

        return products;
    }

    private Product createDemoProduct(String id, String slug, String name, String category,
                                      String description, String materials, double price) {
        Product product = new Product();
        product.setId(id);
        product.setSlug(slug);
        product.setName(name);
        product.setDescription(description);
        product.setMaterials(materials);
        product.setPrice(price);
        product.setCategorySlug(category);
        product.setFeatured(true);
        product.setSortOrder(Integer.parseInt(id));
        product.setCreatedAt(System.currentTimeMillis());

        // Create a simple demo image (light gray placeholder)
        String demoImageBase64 = createPlaceholderImage();
        product.setImageBase64(demoImageBase64);

        return product;
    }

    private String createPlaceholderImage() {
        // Simple gray placeholder image in base64 (100x100 PNG)
        return "iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw7pVKAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAC0lEQVRoge3BMQEAAADCoPVPbQhfoAAAAAAAAAAAAAAAAAAAADg9IAABT4KfkgAAAA==";
    }
}
