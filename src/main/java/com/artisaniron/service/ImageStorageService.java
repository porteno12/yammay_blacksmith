package com.artisaniron.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class ImageStorageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageStorageService.class);
    private static final int MAX_WIDTH = 1200;
    private static final int MAX_HEIGHT = 800;
    private static final int JPEG_QUALITY = 80;
    private static final long MAX_SIZE_BYTES = 200 * 1024;

    public String toBase64(MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();

        if (imageBytes.length > MAX_SIZE_BYTES * 2) {
            imageBytes = compressImage(imageBytes);
        }

        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public String fileToBase64(java.io.File file) throws IOException {
        byte[] imageBytes = java.nio.file.Files.readAllBytes(file.toPath());

        if (imageBytes.length > MAX_SIZE_BYTES * 2) {
            imageBytes = compressImage(imageBytes);
        }

        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private byte[] compressImage(byte[] imageData) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));

        if (originalImage == null) {
            return imageData;
        }

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        if (width > MAX_WIDTH || height > MAX_HEIGHT) {
            float scale = Math.min((float) MAX_WIDTH / width, (float) MAX_HEIGHT / height);
            width = (int) (width * scale);
            height = (int) (height * scale);

            java.awt.Image scaledImage = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            java.awt.Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            originalImage = resizedImage;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        java.awt.image.BufferedImage jpegImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        java.awt.Graphics2D graphics = jpegImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
        graphics.dispose();

        ImageIO.write(jpegImage, "jpg", outputStream);
        return outputStream.toByteArray();
    }

    public String dataUriFromBase64(String base64String, String contentType) {
        if (contentType == null || contentType.isEmpty()) {
            contentType = "image/jpeg";
        }
        return "data:" + contentType + ";base64," + base64String;
    }
}
