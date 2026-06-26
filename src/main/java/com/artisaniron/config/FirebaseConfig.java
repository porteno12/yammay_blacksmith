package com.artisaniron.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @Value("${firebase.database-url:}")
    private String databaseUrl;

    @Value("${firebase.service-account-json:}")
    private String serviceAccountJson;

    @Bean
    @ConditionalOnMissingBean
    public FirebaseDatabase firebaseDatabase() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            if (serviceAccountJson == null || serviceAccountJson.isEmpty()) {
                logger.warn("Firebase service account JSON not configured. Using application default credentials.");
                FirebaseOptions options = FirebaseOptions.builder()
                        .setDatabaseUrl(databaseUrl)
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .build();
                FirebaseApp.initializeApp(options);
            } else {
                logger.info("Initializing Firebase with service account JSON");
                ByteArrayInputStream stream = new ByteArrayInputStream(
                        serviceAccountJson.getBytes(StandardCharsets.UTF_8)
                );
                FirebaseOptions options = FirebaseOptions.builder()
                        .setDatabaseUrl(databaseUrl)
                        .setCredentials(GoogleCredentials.fromStream(stream))
                        .build();
                FirebaseApp.initializeApp(options);
            }
        }
        return FirebaseDatabase.getInstance(databaseUrl);
    }
}
