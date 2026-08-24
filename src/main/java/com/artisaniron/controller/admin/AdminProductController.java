package com.artisaniron.controller.admin;

import com.artisaniron.exception.InvalidImageException;
import com.artisaniron.exception.ProductNotFoundException;
import com.artisaniron.model.Product;
import com.artisaniron.service.ImageStorageService;
import com.artisaniron.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin/products")
@PreAuthorize("hasRole('ADMIN')")
public class AdminProductController {
    private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);
    private static final int MAX_GALLERY_IMAGES = 6;
    private static final Pattern EDIT_REDIRECT = Pattern.compile("^/admin/products/[^/]+/edit$");
    private static final Pattern GALLERY_REDIRECT = Pattern.compile("^/gallery(\\?category=[a-zA-Z0-9-]+)?$");

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

    @GetMapping("/{id}/edit")
    public String editProductForm(@PathVariable String id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));

        model.addAttribute("product", product);
        model.addAttribute("categories", productService.getCategories());
        return "admin/product-form";
    }

    @PostMapping
    public String saveProduct(
            @RequestParam(required = false) String id,
            @RequestParam String slug,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String materials,
            @RequestParam double price,
            @RequestParam String categorySlug,
            @RequestParam(required = false) boolean featured,
            @RequestParam(required = false) int sortOrder,
            @RequestParam(required = false) MultipartFile image,
            RedirectAttributes redirectAttributes) throws IOException {

        Product product;
        if (id != null && !id.isBlank()) {
            product = productService.getProductById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        } else {
            product = new Product();
        }

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
        redirectAttributes.addFlashAttribute("successMessage", "המוצר נשמר בהצלחה!");

        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/images")
    public String addGalleryImages(
            @PathVariable String id,
            @RequestParam(required = false) List<MultipartFile> images,
            @RequestParam(required = false) String redirectTo,
            RedirectAttributes redirectAttributes) throws IOException {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));

        String target = safeRedirect(redirectTo, id);

        if (images == null || images.isEmpty()) {
            return "redirect:" + target;
        }

        List<String> gallery = product.getGalleryImages();
        if (gallery == null) {
            gallery = new ArrayList<>();
        }

        int available = MAX_GALLERY_IMAGES - gallery.size();
        int toAdd = 0;
        boolean overflow = false;

        for (MultipartFile file : images) {
            if (file == null || file.isEmpty()) {
                continue;
            }
            if (toAdd >= available) {
                overflow = true;
                break;
            }
            gallery.add(imageStorageService.toBase64(file));
            toAdd++;
        }

        product.setGalleryImages(gallery);
        productService.saveProduct(product);

        if (overflow) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "ניתן להוסיף עד " + MAX_GALLERY_IMAGES + " תמונות נוספות למוצר. חלק מהתמונות לא נשמרו.");
        } else if (toAdd > 0) {
            redirectAttributes.addFlashAttribute("successMessage", "התמונות נוספו בהצלחה!");
        }

        return "redirect:" + target;
    }

    @PostMapping("/{id}/images/{index}/delete")
    public String deleteGalleryImage(
            @PathVariable String id,
            @PathVariable int index,
            @RequestParam(required = false) String redirectTo,
            RedirectAttributes redirectAttributes) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));

        String target = safeRedirect(redirectTo, id);

        List<String> gallery = product.getGalleryImages();
        if (gallery != null && index >= 0 && index < gallery.size()) {
            gallery.remove(index);
            product.setGalleryImages(gallery);
            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "התמונה נמחקה בהצלחה!");
        }

        return "redirect:" + target;
    }

    private String safeRedirect(String redirectTo, String productId) {
        if (redirectTo != null) {
            if (redirectTo.equals("/admin/products/" + productId + "/edit")) {
                return redirectTo;
            }
            if (GALLERY_REDIRECT.matcher(redirectTo).matches()) {
                return redirectTo;
            }
        }
        return "/admin/products/" + productId + "/edit";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleUploadTooLarge(MaxUploadSizeExceededException ex, RedirectAttributes redirectAttributes,
                                        jakarta.servlet.http.HttpServletRequest request) {
        logger.warn("Upload rejected: file too large", ex);
        redirectAttributes.addFlashAttribute("errorMessage", "הקובץ שהועלה גדול מדי");
        return "redirect:" + fallbackRedirect(request);
    }

    @ExceptionHandler(InvalidImageException.class)
    public String handleInvalidImage(InvalidImageException ex, RedirectAttributes redirectAttributes,
                                      jakarta.servlet.http.HttpServletRequest request) {
        logger.warn("Upload rejected: invalid image", ex);
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:" + fallbackRedirect(request);
    }

    private String fallbackRedirect(jakarta.servlet.http.HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer != null) {
            String path = referer.replaceFirst("^https?://[^/]+", "");
            if (EDIT_REDIRECT.matcher(path).matches() || GALLERY_REDIRECT.matcher(path).matches()
                    || path.equals("/admin/products") || path.startsWith("/admin/products/")) {
                return path;
            }
        }
        return "/admin/products";
    }
}
