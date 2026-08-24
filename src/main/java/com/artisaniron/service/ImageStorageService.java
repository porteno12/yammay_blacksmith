package com.artisaniron.service;

import com.artisaniron.exception.InvalidImageException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Locale;

@Service
public class ImageStorageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageStorageService.class);
    private static final int MAX_WIDTH = 1600;
    private static final int MAX_HEIGHT = 1200;
    private static final long MAX_SIZE_BYTES = 200 * 1024;
    private static final float MIN_QUALITY = 0.4f;
    private static final float QUALITY_STEP = 0.1f;

    public String toBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(normalizeImage(file));
    }

    public String fileToBase64(java.io.File file) throws IOException {
        rejectUnsupportedFormat(null, file.getName());
        byte[] imageBytes = java.nio.file.Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(normalizeBytes(imageBytes));
    }

    public byte[] normalizeImage(MultipartFile file) throws IOException {
        rejectUnsupportedFormat(file.getContentType(), file.getOriginalFilename());
        return normalizeBytes(file.getBytes());
    }

    private byte[] normalizeBytes(byte[] original) throws IOException {
        float quality = 0.8f;
        byte[] result = resizeAndEncode(original, quality);

        while (result.length > MAX_SIZE_BYTES && quality > MIN_QUALITY) {
            quality = Math.max(MIN_QUALITY, quality - QUALITY_STEP);
            result = resizeAndEncode(original, quality);
        }

        return result;
    }

    private byte[] resizeAndEncode(byte[] original, float quality) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Dimension dimension = probeDimensions(original);
            var builder = Thumbnails.of(new ByteArrayInputStream(original))
                    .useExifOrientation(true)
                    .outputFormat("jpg")
                    .outputQuality(quality);

            if (dimension.width > MAX_WIDTH || dimension.height > MAX_HEIGHT) {
                builder.size(MAX_WIDTH, MAX_HEIGHT);
            } else {
                builder.scale(1.0);
            }

            builder.toOutputStream(outputStream);
        } catch (IOException e) {
            logger.warn("Failed to decode/resize uploaded image", e);
            throw new InvalidImageException("הקובץ שהועלה אינו תמונה תקינה", e);
        }
        return outputStream.toByteArray();
    }

    private Dimension probeDimensions(byte[] imageBytes) throws IOException {
        try (ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(imageBytes))) {
            if (iis == null) {
                throw new InvalidImageException("הקובץ שהועלה אינו תמונה תקינה");
            }
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                throw new InvalidImageException("הקובץ שהועלה אינו תמונה תקינה");
            }
            ImageReader reader = readers.next();
            try {
                reader.setInput(iis);
                return new Dimension(reader.getWidth(0), reader.getHeight(0));
            } finally {
                reader.dispose();
            }
        }
    }

    private void rejectUnsupportedFormat(String contentType, String filename) {
        String type = contentType == null ? "" : contentType.toLowerCase(Locale.ROOT);
        String name = filename == null ? "" : filename.toLowerCase(Locale.ROOT);
        if (type.contains("svg") || name.endsWith(".svg")) {
            throw new InvalidImageException("קבצי SVG אינם נתמכים");
        }
    }

    public String dataUriFromBase64(String base64String, String contentType) {
        if (contentType == null || contentType.isEmpty()) {
            contentType = "image/jpeg";
        }
        return "data:" + contentType + ";base64," + base64String;
    }
}
