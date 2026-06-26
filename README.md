# Artisan Iron - Blacksmith Workshop Web App

A Spring Boot web application for showcasing custom ironwork and managing client inquiries.

## Setup

### Prerequisites
- Java 17+
- Maven 3.6+
- Firebase account with Realtime Database enabled

### Configuration

1. **Create Firebase Project**
   - Go to https://console.firebase.google.com
   - Create a new project
   - Enable Realtime Database
   - Download service account JSON key

2. **Environment Variables**
   ```bash
   export FIREBASE_DATABASE_URL=https://your-project-default-rtdb.firebaseio.com
   export FIREBASE_SERVICE_ACCOUNT_JSON='{"type":"service_account","project_id":"..."}' 
   export MAIL_PASSWORD=your-sendgrid-api-key
   export ADMIN_PASSWORD=your-secure-admin-password
   ```

3. **Local Development**
   ```bash
   cd artisan-iron-web
   ./mvnw spring-boot:run
   ```

   Visit http://localhost:8080

4. **Build Production JAR**
   ```bash
   ./mvnw clean package
   ```

## Project Structure

- **Model:** Product, Category, ContactMessage, InquiryRequest (POJOs)
- **Repository:** Firebase RTDB access layer
- **Service:** Business logic (ProductService, ContactService, InquiryService, etc.)
- **Controller:** HTTP request handlers for public and admin routes
- **Templates:** Thymeleaf HTML templates (server-side rendering)

## Key Features

- **Public Site:** Home, Gallery, Product Details, Services, About, Contact
- **Customer Forms:** Contact form, Custom Order Inquiry
- **Admin Panel:** Product management, submission viewing (password-protected)
- **Images:** Base64-encoded, stored directly in Firebase RTDB
- **Email:** SendGrid SMTP for confirmations and admin notifications
- **Caching:** Caffeine in-memory cache for product gallery

## Deployment

### Railway.app
1. Push to GitHub
2. Connect repository to Railway
3. Set environment variables in Railway dashboard
4. Deploy

### Render.com
1. Connect GitHub repo to Render
2. Select "Web Service"
3. Set environment variables
4. Deploy

## Admin Access

- **URL:** https://your-domain.com/admin
- **Username:** admin
- **Password:** (set via ADMIN_PASSWORD env var)

## Database

All data is stored in Firebase Realtime Database:
- `products/` - Product listings
- `contactMessages/` - Contact form submissions
- `inquiries/` - Custom order inquiries

Images are stored as Base64 strings in product nodes.

## Notes

- Images are compressed to <200KB before Base64 encoding
- RTDB push keys are automatically generated and stored as `id` in each entity
- Categories are seeded at startup and cached in memory
- All forms have server-side validation using JSR-380 annotations
