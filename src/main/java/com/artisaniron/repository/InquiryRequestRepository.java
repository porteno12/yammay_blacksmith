package com.artisaniron.repository;

import com.artisaniron.model.InquiryRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class InquiryRequestRepository {
    private final FirebaseDatabase firebaseDatabase;
    private static final String COLLECTION = "inquiries";

    public InquiryRequestRepository(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public List<InquiryRequest> findAll() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<InquiryRequest> inquiries = new ArrayList<>();

        firebaseDatabase.getReference(COLLECTION)
                .orderByChild("submittedAt")
                .addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            InquiryRequest inquiry = child.getValue(InquiryRequest.class);
                            if (inquiry != null) {
                                inquiries.add(inquiry);
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
        return inquiries;
    }

    public InquiryRequest save(InquiryRequest inquiry) {
        String key = inquiry.getId();
        if (key == null || key.isEmpty()) {
            key = firebaseDatabase.getReference(COLLECTION).push().getKey();
        }
        inquiry.setId(key);
        firebaseDatabase.getReference(COLLECTION).child(key).setValueAsync(inquiry);
        return inquiry;
    }

    public void markAsRead(String id) {
        firebaseDatabase.getReference(COLLECTION).child(id).child("read").setValueAsync(true);
    }

    public void updateStatus(String id, String status) {
        firebaseDatabase.getReference(COLLECTION).child(id).child("status").setValueAsync(status);
    }
}
