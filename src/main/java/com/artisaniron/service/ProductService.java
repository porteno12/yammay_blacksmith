package com.artisaniron.service;

import com.artisaniron.model.Category;
import com.artisaniron.model.Product;
import com.artisaniron.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final List<Category> categories = new ArrayList<>();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        initializeCategories();
    }

    private void initializeCategories() {
        categories.add(new Category("1", "gates", "שערים"));
        categories.add(new Category("2", "railings", "מעקות"));
        categories.add(new Category("3", "decor", "עיצוב חיצוני"));
        categories.add(new Category("4", "custom", "עבודות מותאמות"));
    }

    @Cacheable("products")
    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (InterruptedException e) {
            logger.error("Interrupted while fetching all products", e);
            Thread.currentThread().interrupt();
            return new ArrayList<>();
        }
    }

    @Cacheable("products")
    public List<Product> getProductsByCategory(String categorySlug) {
        try {
            return productRepository.findByCategory(categorySlug);
        } catch (InterruptedException e) {
            logger.error("Interrupted while fetching products by category: {}", categorySlug, e);
            Thread.currentThread().interrupt();
            return new ArrayList<>();
        }
    }

    public Optional<Product> getProductById(String id) {
        try {
            return productRepository.findById(id);
        } catch (InterruptedException e) {
            logger.error("Interrupted while fetching product by id: {}", id, e);
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }

    public Optional<Product> getProductBySlug(String slug) {
        try {
            return productRepository.findBySlug(slug);
        } catch (InterruptedException e) {
            logger.error("Interrupted while fetching product by slug: {}", slug, e);
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }

    public List<Product> getFeaturedProducts() {
        return getAllProducts().stream()
                .filter(Product::isFeatured)
                .limit(3)
                .toList();
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product saveProduct(Product product) {
        if (product.getCreatedAt() == 0) {
            product.setCreatedAt(System.currentTimeMillis());
        }
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(String id) {
        productRepository.delete(id);
    }

    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public Optional<Category> getCategoryBySlug(String slug) {
        return categories.stream()
                .filter(c -> c.getSlug().equals(slug))
                .findFirst();
    }
}
