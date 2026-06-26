package com.artisaniron.repository;

import com.artisaniron.model.ContactMessage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class ContactMessageRepository {
    private final FirebaseDatabase firebaseDatabase;
    private static final String COLLECTION = "contactMessages";

    public ContactMessageRepository(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public List<ContactMessage> findAll() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<ContactMessage> messages = new ArrayList<>();

        firebaseDatabase.getReference(COLLECTION)
                .orderByChild("submittedAt")
                .addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            ContactMessage msg = child.getValue(ContactMessage.class);
                            if (msg != null) {
                                messages.add(msg);
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
        return messages;
    }

    public ContactMessage save(ContactMessage message) {
        String key = message.getId();
        if (key == null || key.isEmpty()) {
            key = firebaseDatabase.getReference(COLLECTION).push().getKey();
        }
        message.setId(key);
        firebaseDatabase.getReference(COLLECTION).child(key).setValue(message);
        return message;
    }

    public void markAsRead(String id) {
        firebaseDatabase.getReference(COLLECTION).child(id).child("read").setValue(true);
    }
}
