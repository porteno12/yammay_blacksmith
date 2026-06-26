package com.artisaniron.repository;

import com.artisaniron.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Repository
public class ProductRepository {
    private final FirebaseDatabase firebaseDatabase;
    private static final String COLLECTION = "products";

    public ProductRepository(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public List<Product> findAll() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<Product> products = new ArrayList<>();

        firebaseDatabase.getReference(COLLECTION)
                .orderByChild("sortOrder")
                .addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            Product product = child.getValue(Product.class);
                            if (product != null) {
                                products.add(product);
                            }
                        }
                        latch.countDown();
                    }

                    @Override
                    public void onCancelled(com.google.firebase.database.DatabaseError error) {
                        latch.countDown();
                    }
                });

        latch.await();
        return products;
    }

    public Optional<Product> findBySlug(String slug) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Product[] result = {null};

        firebaseDatabase.getReference(COLLECTION)
                .orderByChild("slug")
                .equalTo(slug)
                .addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            result[0] = child.getValue(Product.class);
                            break;
                        }
                        latch.countDown();
                    }

                    @Override
                    public void onCancelled(com.google.firebase.database.DatabaseError error) {
                        latch.countDown();
                    }
                });

        latch.await();
        return Optional.ofNullable(result[0]);
    }

    public List<Product> findByCategory(String categorySlug) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<Product> products = new ArrayList<>();

        firebaseDatabase.getReference(COLLECTION)
                .orderByChild("categorySlug")
                .equalTo(categorySlug)
                .addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            Product product = child.getValue(Product.class);
                            if (product != null) {
                                products.add(product);
                            }
                        }
                        latch.countDown();
                    }

                    @Override
                    public void onCancelled(com.google.firebase.database.DatabaseError error) {
                        latch.countDown();
                    }
                });

        latch.await();
        return products;
    }

    public Product save(Product product) {
        String key = product.getId();
        if (key == null || key.isEmpty()) {
            key = firebaseDatabase.getReference(COLLECTION).push().getKey();
        }
        product.setId(key);
        firebaseDatabase.getReference(COLLECTION).child(key).setValue(product);
        return product;
    }

    public void delete(String id) {
        firebaseDatabase.getReference(COLLECTION).child(id).removeValue();
    }
}
