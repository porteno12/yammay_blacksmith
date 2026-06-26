# Copy & Paste Commands - Quick Run Guide

**Copy and paste these commands exactly as shown.** Replace the values in `<brackets>` with your actual values.

---

## Step 1: Check Prerequisites

Open Terminal/PowerShell and run:

**Mac/Linux:**
```bash
java -version
mvn -version
```

**Windows (PowerShell):**
```powershell
java -version
mvn -version
```

You should see Java 17+ and Maven 3.6+. If not, install them:
- Java: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- Maven: https://maven.apache.org/download.cgi

---

## Step 2: Get Firebase Credentials

1. Go to https://console.firebase.google.com
2. Create new project (name: `artisan-iron`)
3. Enable Realtime Database (Test Mode)
4. Download service account JSON (move it somewhere safe)
5. Copy the database URL from the database page

**You'll have:**
- Database URL: `https://artisan-iron-abc123-default-rtdb.firebaseio.com`
- JSON file path: `/path/to/firebase-json-file.json`

---

## Step 3: Set Environment Variables

**Mac/Linux (Terminal):**

Copy and paste this entire block:
```bash
export FIREBASE_DATABASE_URL=https://artisan-iron-abc123-default-rtdb.firebaseio.com
export FIREBASE_SERVICE_ACCOUNT_PATH=/path/to/firebase-json-file.json
export MAIL_PASSWORD=test
export ADMIN_PASSWORD=admin123
```

**Then replace:**
- `artisan-iron-abc123` with your actual Firebase project ID
- `/path/to/firebase-json-file.json` with the path to your JSON file

**Windows (PowerShell):**

Copy and paste this entire block:
```powershell
$env:FIREBASE_DATABASE_URL="https://artisan-iron-abc123-default-rtdb.firebaseio.com"
$env:FIREBASE_SERVICE_ACCOUNT_PATH="C:\Users\YourName\Downloads\firebase-json-file.json"
$env:MAIL_PASSWORD="test"
$env:ADMIN_PASSWORD="admin123"
```

**Then replace:**
- `artisan-iron-abc123` with your actual Firebase project ID
- `C:\Users\YourName\Downloads\` with your actual path to the JSON file

---

## Step 4: Start the App

**Mac/Linux (Terminal):**

Copy and paste:
```bash
cd /Users/wizo/Yammay_Blacksmith/artisan-iron-web
./mvnw spring-boot:run
```

**Windows (PowerShell):**

Copy and paste:
```powershell
cd C:\Users\YourName\path\to\artisan-iron-web
mvn spring-boot:run
```

(Or just use `./mvnw spring-boot:run` if mvn doesn't work)

---

## Step 5: Open Browser

Once you see:
```
Started ArtisanIronApplication in X.XXX seconds
```

Open your browser to:
```
http://localhost:8080
```

You should see the home page! ✅

---

## Test the App

1. **Home** → http://localhost:8080/
2. **Gallery** → http://localhost:8080/gallery
3. **Contact Form** → http://localhost:8080/contact (submit a test message)
4. **Admin Panel** → http://localhost:8080/admin
   - Username: `admin`
   - Password: `admin123` (or whatever you set)
5. **Add Product** → Click "Add New Product" and upload an image

---

## Stop the App

Press **Ctrl+C** in the terminal

---

## If Something Goes Wrong

**Port 8080 in use?**
```bash
# Mac/Linux: Kill the process
lsof -i :8080
kill -9 <PID>
```

**Can't find Firebase?**
```bash
# Verify env vars are set:
echo $FIREBASE_DATABASE_URL
echo $FIREBASE_SERVICE_ACCOUNT_PATH
echo $ADMIN_PASSWORD

# If empty, re-run the export commands from Step 3
```

**Maven not working?**
```bash
# Try without the ./
mvn spring-boot:run
```

---

## Complete Example

Here's what the whole process looks like with real values:

### Step 2: Firebase Setup (DONE → You have these)
```
Database URL: https://my-blacksmith-default-rtdb.firebaseio.com
JSON file: /Users/john/Downloads/my-blacksmith-firebase-adminsdk-xyz.json
```

### Step 3: Environment Variables (COPY & PASTE)
```bash
export FIREBASE_DATABASE_URL=https://my-blacksmith-default-rtdb.firebaseio.com
export FIREBASE_SERVICE_ACCOUNT_PATH=/Users/john/Downloads/my-blacksmith-firebase-adminsdk-xyz.json
export MAIL_PASSWORD=test
export ADMIN_PASSWORD=mypassword123
```

### Step 4: Start App (COPY & PASTE)
```bash
cd /Users/wizo/Yammay_Blacksmith/artisan-iron-web
./mvnw spring-boot:run
```

### Step 5: Open Browser
```
http://localhost:8080
```

Done! ✅

---

## Make Changes and Reload

The app watches for Java file changes:

1. **Edit a Java file** → Save it
2. **App auto-restarts** → Look for "Restarted Application in X seconds"
3. **Refresh browser** → See the changes

For HTML/CSS changes:
1. **Edit template or CSS** → Save it
2. **Refresh browser** → See the changes

---

That's it! If you follow these steps exactly, the app will run. 🚀
