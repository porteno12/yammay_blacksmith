# 🎯 START HERE - Run Artisan Iron Locally

This is your complete guide to running the app on your machine before deployment.

---

## ⏱️ Time Required: 15-20 minutes

1. **Create Firebase project** (~5 min)
2. **Set up environment** (~3 min)
3. **Start the app** (~7 min first time)
4. **Test it** (~5 min)

---

## 📋 Prerequisites

You need these installed on your machine:

- **Java 17+** — Check with `java -version`
- **Maven 3.6+** — Check with `mvn -version`

If you don't have them:
- **Java:** https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- **Maven:** https://maven.apache.org/download.cgi

---

## 🔥 Quick Path (Copy & Paste)

### Option 1: Use the Setup Script (Easiest)

**Mac/Linux:**
```bash
cd /Users/wizo/Yammay_Blacksmith/artisan-iron-web
chmod +x RUN_LOCALLY.sh
./RUN_LOCALLY.sh
```

**Windows (PowerShell):** Manual steps below (script doesn't work on Windows)

---

### Option 2: Manual Setup

#### Step 1: Create Firebase Project
1. Go to https://console.firebase.google.com
2. Create new project (name: `artisan-iron`)
3. Enable Realtime Database (Test Mode)
4. Download service account JSON
5. Copy database URL

**Full details:** See `FIREBASE_SETUP.md`

---

#### Step 2: Set Environment Variables

**Mac/Linux Terminal:**
```bash
export FIREBASE_DATABASE_URL=https://your-project-default-rtdb.firebaseio.com
export FIREBASE_SERVICE_ACCOUNT_PATH=/path/to/your-firebase-json.json
export MAIL_PASSWORD=test
export ADMIN_PASSWORD=admin123
```

**Windows PowerShell:**
```powershell
$env:FIREBASE_DATABASE_URL="https://your-project-default-rtdb.firebaseio.com"
$env:FIREBASE_SERVICE_ACCOUNT_PATH="C:\path\to\your-firebase-json.json"
$env:MAIL_PASSWORD="test"
$env:ADMIN_PASSWORD="admin123"
```

**Replace:**
- `your-project-default-rtdb.firebaseio.com` with your actual Firebase database URL
- `/path/to/your-firebase-json.json` with the path where you downloaded the JSON file

---

#### Step 3: Start the App

**First time only** (downloads dependencies, takes 2-3 minutes):
```bash
cd /Users/wizo/Yammay_Blacksmith/artisan-iron-web
./mvnw clean
```

**Then start the app:**
```bash
./mvnw spring-boot:run
```

**Windows (if mvnw doesn't work):**
```powershell
mvn spring-boot:run
```

You should see:
```
...
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_|\__, | / / / /
...
Started ArtisanIronApplication in 5.123 seconds
```

---

#### Step 4: Open Browser

Go to **http://localhost:8080** and you'll see the home page! 🎉

---

## ✅ What to Test

### Public Pages
- [x] **Home** → http://localhost:8080/
- [x] **Gallery** → http://localhost:8080/gallery
- [x] **About** → http://localhost:8080/about
- [x] **Services** → http://localhost:8080/services
- [x] **Contact** → http://localhost:8080/contact (try submitting a message)
- [x] **Inquiry** → http://localhost:8080/inquiry (try submitting)

### Admin Panel
- [x] **Dashboard** → http://localhost:8080/admin
  - Username: `admin`
  - Password: (whatever you set in `ADMIN_PASSWORD`)
- [x] **Add Product** → Add a test product with an image
- [x] **View Gallery** → Check that the new product appears
- [x] **View Submissions** → See contact messages and inquiry requests

---

## 🔍 Verify Data in Firebase

After submitting a form:

1. Go to Firebase Console → Realtime Database
2. You should see data under `contactMessages/` or `inquiries/`
3. This proves the app is talking to Firebase! ✅

---

## 🛑 Stop the App

Press **Ctrl+C** in the terminal where it's running.

---

## 🐛 If Something Goes Wrong

### App won't start

**Error:** `Port 8080 already in use`
```bash
# Find and kill the process
lsof -i :8080
kill -9 <PID>
# Or change port in src/main/resources/application.yml:
# server.port=8081
```

**Error:** `Cannot find FIREBASE_DATABASE_URL`
```bash
# Verify env vars are set:
echo $FIREBASE_DATABASE_URL
echo $FIREBASE_SERVICE_ACCOUNT_PATH

# If empty, re-export them:
export FIREBASE_DATABASE_URL=https://...
export FIREBASE_SERVICE_ACCOUNT_PATH=/path/to/json
```

**Error:** `Service account JSON file not found`
```bash
# Verify the file exists:
ls -la /path/to/firebase-json.json

# If it doesn't, download it again from Firebase console
```

### Forms don't save data

**Issue:** Data not appearing in Firebase after submitting a form

**Check:**
1. Is Realtime Database enabled in Firebase console?
2. Is the database URL correct?
3. Check browser console for JavaScript errors (F12)
4. Check app terminal for error messages

---

## 📚 Detailed Guides

- **`QUICK_START.md`** — 5-minute overview
- **`LOCAL_SETUP.md`** — Complete local development guide
- **`FIREBASE_SETUP.md`** — Step-by-step Firebase configuration
- **`README.md`** — Project structure & deployment

---

## 🚀 Ready for Deployment?

Once you've tested everything locally:

1. Read `README.md` for deployment options
2. Choose Railway.app or Render.com
3. Deploy and you're live!

---

## ❓ Common Questions

**Q: Can I change the admin password?**  
A: Yes, just set a different `ADMIN_PASSWORD` env var and restart the app.

**Q: Can I use a different port?**  
A: Yes, edit `src/main/resources/application.yml` and change `server.port: 8080` to another port (e.g., `8081`).

**Q: How do I add more products?**  
A: Use the admin panel at http://localhost:8080/admin → Products → Add New Product

**Q: How do I see what's in Firebase?**  
A: Go to Firebase console → Realtime Database → You'll see the JSON tree with all your data.

**Q: Do I need SendGrid for local testing?**  
A: No, leave `MAIL_PASSWORD` empty or set it to anything. Emails will fail silently but the app still works.

---

## 🎯 Next Steps

1. **Now:** Start the app with the steps above
2. **Test:** Submit a contact form and verify data appears in Firebase
3. **Explore:** Add products via the admin panel
4. **Then:** Read `README.md` to deploy to Railway or Render

---

## 💬 Need Help?

If something isn't working:
1. Read the error message carefully
2. Check the appropriate guide above (`FIREBASE_SETUP.md`, `LOCAL_SETUP.md`)
3. Check the Troubleshooting section above

**All guides are in the same folder as this file.**

---

## 🎉 You're Ready!

Go ahead and run the app. It should work! 

**Good luck!** 🚀
