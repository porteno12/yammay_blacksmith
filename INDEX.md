# 📚 Artisan Iron - Documentation Index

All the documentation you need to run and deploy the app.

## 🚀 Getting Started

**Start here** if you haven't run the app yet:

1. [**START_HERE.md**](START_HERE.md) - Complete setup guide (15-20 min)
   - Prerequisites
   - Step-by-step instructions
   - Troubleshooting

2. [**INSTALLATION_SUMMARY.txt**](INSTALLATION_SUMMARY.txt) - Quick reference card
   - Copy & paste commands
   - Important URLs
   - File locations

## 📋 Detailed Guides

**Read these for specific topics:**

- [**QUICK_START.md**](QUICK_START.md) - 5-minute quick start
  - Fastest way to get running
  - Basic troubleshooting

- [**FIREBASE_SETUP.md**](FIREBASE_SETUP.md) - Firebase configuration
  - Create Firebase project
  - Download credentials
  - Set environment variables
  - Security notes for production

- [**LOCAL_SETUP.md**](LOCAL_SETUP.md) - Full local development guide
  - Prerequisites with links
  - Firebase setup details
  - SendGrid email (optional)
  - Environment variables
  - Running the app
  - Seeding sample data
  - Troubleshooting
  - Development workflow

- [**README.md**](README.md) - Project overview
  - Setup instructions
  - Project structure
  - Key features
  - Deployment options
  - Database schema
  - Notes

## 🛠️ Scripts

- [**RUN_LOCALLY.sh**](RUN_LOCALLY.sh) - Automated setup (Mac/Linux)
  - Checks prerequisites
  - Collects configuration
  - Starts the app

## 📁 Project Structure

```
artisan-iron-web/
├── pom.xml                          # Maven dependencies
├── INDEX.md                         # This file
├── START_HERE.md                    # ← Read first
├── INSTALLATION_SUMMARY.txt         # Quick reference
├── QUICK_START.md                   # 5-minute guide
├── LOCAL_SETUP.md                   # Detailed guide
├── FIREBASE_SETUP.md                # Firebase config
├── README.md                        # Project overview
├── RUN_LOCALLY.sh                   # Setup script
│
├── src/main/java/com/artisaniron/
│   ├── ArtisanIronApplication.java
│   ├── config/                      # Firebase, Security, Cache, Mail
│   ├── controller/                  # HTTP handlers
│   ├── service/                     # Business logic
│   ├── repository/                  # Firebase data access
│   ├── model/                       # Data classes
│   ├── dto/                         # Form DTOs
│   └── exception/                   # Error handling
│
└── src/main/resources/
    ├── application.yml              # Spring config
    ├── templates/                   # Thymeleaf HTML
    │   ├── layout/base.html
    │   ├── index.html
    │   ├── gallery.html
    │   ├── product-detail.html
    │   ├── about.html
    │   ├── services.html
    │   ├── contact.html
    │   ├── inquiry.html
    │   ├── contact-success.html
    │   ├── inquiry-success.html
    │   ├── error/404.html
    │   ├── error/500.html
    │   └── admin/
    │       ├── dashboard.html
    │       ├── products.html
    │       ├── product-form.html
    │       └── submissions.html
    └── static/
        ├── css/main.css
        └── js/main.js
```

## 🎯 Common Tasks

### "How do I start the app?"
→ Read [START_HERE.md](START_HERE.md) (easiest) or [QUICK_START.md](QUICK_START.md) (fastest)

### "How do I set up Firebase?"
→ Read [FIREBASE_SETUP.md](FIREBASE_SETUP.md)

### "How do I add products?"
→ Run the app, go to http://localhost:8080/admin and use the Products page

### "How do I see the data?"
→ Go to Firebase console → Realtime Database and look at the JSON tree

### "How do I deploy to production?"
→ Read [README.md](README.md) → Deployment section

### "The app won't start. What do I do?"
→ Read the Troubleshooting section in [START_HERE.md](START_HERE.md) or [LOCAL_SETUP.md](LOCAL_SETUP.md)

## 📊 What the App Does

- **Public Site:** Home, Gallery, Product Details, Services, About, Contact
- **Forms:** Contact form, Custom order inquiry form
- **Admin Panel:** Product management, view submissions
- **Database:** Firebase Realtime Database (all data stored as JSON)
- **Images:** Base64-encoded, stored in database
- **Email:** SendGrid SMTP for notifications
- **Language:** Hebrew (RTL layout)

## 🔧 Technology Stack

- **Backend:** Spring Boot 3.3.0
- **Language:** Java 17
- **Database:** Firebase Realtime Database
- **Templates:** Thymeleaf (server-side rendering)
- **Styling:** CSS (custom, Hebrew RTL)
- **Security:** Spring Security with BCrypt
- **Email:** SendGrid SMTP
- **Caching:** Caffeine
- **Build:** Maven

## 🌍 Deployment Platforms

After testing locally, deploy to:
- **Railway.app** - Simple, fast, auto-deploys from GitHub
- **Render.com** - Similar to Railway, good free tier
- **DigitalOcean App Platform** - More control, slightly more complex

See [README.md](README.md) for deployment steps.

## ✅ Quick Checklist

Before deployment, verify:
- [ ] App runs locally on http://localhost:8080
- [ ] Home page loads and displays correctly
- [ ] Gallery shows products (add at least one via admin)
- [ ] Contact form submits and appears in Firebase
- [ ] Inquiry form submits and appears in Firebase
- [ ] Admin panel lets you add/delete products
- [ ] Images upload and display correctly
- [ ] Hebrew text displays correctly (RTL)
- [ ] Mobile responsive (test on phone)

## 🆘 Need Help?

1. **Check the right guide** - Use the navigation above to find what you need
2. **Search the guides** - They cover most problems
3. **Check Firebase console** - Verify the database is enabled and has data
4. **Check environment variables** - Verify they're set correctly
5. **Check app logs** - Look for error messages in the terminal

## 📞 Support Resources

- **Spring Boot:** https://spring.io/projects/spring-boot
- **Firebase Admin SDK:** https://firebase.google.com/docs/database/admin/start
- **Thymeleaf:** https://www.thymeleaf.org/
- **Maven:** https://maven.apache.org/

---

**Ready to get started?** → Open [START_HERE.md](START_HERE.md) 🚀
