package com.artisaniron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ArtisanIronApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtisanIronApplication.class, args);
    }
}
