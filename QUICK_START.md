# Quick Start - Run Locally in 5 Minutes

## The Fast Path

### 1. Install Java (if not already installed)
```bash
java -version
# If you get "command not found", download from:
# https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
```

### 2. Create Firebase Project
- Go to https://console.firebase.google.com
- Create new project (name: "artisan-iron")
- Enable Realtime Database (start in Test Mode)
- Download service account JSON
- Copy database URL (looks like: `https://your-project-default-rtdb.firebaseio.com`)

### 3. Set Environment Variables (Mac/Linux)
```bash
export FIREBASE_DATABASE_URL=https://your-project-default-rtdb.firebaseio.com
export FIREBASE_SERVICE_ACCOUNT_PATH=/path/to/your/firebase-json-file.json
export MAIL_PASSWORD=anything
export ADMIN_PASSWORD=admin123
```

**Windows (PowerShell):**
```powershell
$env:FIREBASE_DATABASE_URL="https://your-project-default-rtdb.firebaseio.com"
$env:FIREBASE_SERVICE_ACCOUNT_PATH="C:\path\to\firebase-json-file.json"
$env:MAIL_PASSWORD="anything"
$env:ADMIN_PASSWORD="admin123"
```

### 4. Run the App
```bash
cd /Users/wizo/Yammay_Blacksmith/artisan-iron-web
./mvnw spring-boot:run
```

### 5. Open Browser
```
http://localhost:8080
```

✅ **Done!** The app is running.

---

## What to Test

1. **Public Pages:** Visit all links in the navigation bar
2. **Contact Form:** Fill and submit → Check Firebase console for data
3. **Inquiry Form:** Fill and submit → Check Firebase console
4. **Admin Panel:** Go to http://localhost:8080/admin
   - Login: username=`admin`, password=`admin123` (or whatever you set)
   - Add a new product (upload an image)
   - Check it appears in gallery
   - View submissions

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| "Port 8080 in use" | Stop other apps or change port in `application.yml` |
| "Firebase connection failed" | Check env vars are set correctly, database is enabled |
| "Maven not found" | Install from https://maven.apache.org/download.cgi |
| "Java not found" | Install Java 17+ |
| "Admin login fails" | Username is `admin`, password is your `ADMIN_PASSWORD` env var |

---

## Next Steps

Once running locally:
1. Test all pages and forms
2. Upload product images in admin panel
3. Submit contact forms and check Firebase
4. Read the full guide in `LOCAL_SETUP.md`
5. When ready, deploy to Railway/Render (see `README.md`)

---

## Need Help?

See `LOCAL_SETUP.md` for detailed explanations and troubleshooting.
