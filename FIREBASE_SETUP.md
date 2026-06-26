# Firebase Setup - Step by Step

This guide shows you exactly how to set up Firebase for local development.

## Step 1: Create a Firebase Project

1. Go to **https://console.firebase.google.com**
2. Click **"Add Project"**
3. Enter project name: **`artisan-iron`** (or your preference)
4. Click **Continue**
5. Choose your location/region (pick closest to you)
6. Click **Create Project**
7. Wait for setup to complete (1-2 minutes)

---

## Step 2: Enable Realtime Database

After project creation:

1. In left sidebar, click **Build** → **Realtime Database**
2. Click **Create Database**
3. When prompted, choose a location (closest to you)
4. **IMPORTANT:** Choose **Test Mode** (for development)
   - Test Mode allows anyone to read/write (safe for local testing)
   - Later we'll change to production rules before deployment
5. Click **Enable**

You'll see a database URL like:
```
https://artisan-iron-default-rtdb.firebaseio.com
```
**Copy this URL** — you'll need it for environment variables.

---

## Step 3: Get Service Account Credentials

This JSON file allows your Java app to access Firebase.

1. Click the **⚙️ gear icon** in top right → **Project Settings**
2. Go to **Service Accounts** tab
3. Click **Generate New Private Key**
4. A JSON file will download (e.g., `artisan-iron-firebase-adminsdk-xyz.json`)
5. **Move it to a safe location** on your computer
   - Example: `~/firebase-keys/artisan-iron-firebase.json`
   - **DO NOT commit this file to Git** (it's a secret!)

The JSON file looks like:
```json
{
  "type": "service_account",
  "project_id": "artisan-iron",
  "private_key_id": "abc123...",
  "private_key": "-----BEGIN PRIVATE KEY-----\n...",
  "client_email": "firebase-adminsdk-xyz@artisan-iron.iam.gserviceaccount.com",
  ...
}
```

---

## Step 4: Set Environment Variables

Now tell your Spring Boot app where to find Firebase.

### On Mac/Linux (Terminal)

Open Terminal and run:
```bash
export FIREBASE_DATABASE_URL=https://artisan-iron-default-rtdb.firebaseio.com
export FIREBASE_SERVICE_ACCOUNT_PATH=~/firebase-keys/artisan-iron-firebase.json
export MAIL_PASSWORD=test
export ADMIN_PASSWORD=admin123
```

**To verify they're set:**
```bash
echo $FIREBASE_DATABASE_URL
echo $FIREBASE_SERVICE_ACCOUNT_PATH
echo $ADMIN_PASSWORD
```

**To make them permanent** (survive terminal restart), add to `~/.bash_profile` or `~/.zshrc`:
```bash
# Add these lines to the end of ~/.zshrc (or ~/.bash_profile for older Macs)
export FIREBASE_DATABASE_URL=https://artisan-iron-default-rtdb.firebaseio.com
export FIREBASE_SERVICE_ACCOUNT_PATH=~/firebase-keys/artisan-iron-firebase.json
export MAIL_PASSWORD=test
export ADMIN_PASSWORD=admin123
```

Then reload:
```bash
source ~/.zshrc
```

---

### On Windows (PowerShell)

Open PowerShell and run:
```powershell
$env:FIREBASE_DATABASE_URL="https://artisan-iron-default-rtdb.firebaseio.com"
$env:FIREBASE_SERVICE_ACCOUNT_PATH="C:\Users\YourUsername\firebase-keys\artisan-iron-firebase.json"
$env:MAIL_PASSWORD="test"
$env:ADMIN_PASSWORD="admin123"
```

**To verify:**
```powershell
echo $env:FIREBASE_DATABASE_URL
echo $env:FIREBASE_SERVICE_ACCOUNT_PATH
```

**To make them permanent**, set them in Windows System Environment Variables:
1. Right-click "This PC" or "My Computer" → **Properties**
2. Click **Advanced system settings**
3. Click **Environment Variables**
4. Under "User variables", click **New**
5. Add each variable:
   - Variable name: `FIREBASE_DATABASE_URL`
   - Variable value: `https://artisan-iron-default-rtdb.firebaseio.com`
6. Repeat for `FIREBASE_SERVICE_ACCOUNT_PATH`, `MAIL_PASSWORD`, `ADMIN_PASSWORD`
7. Click **OK** and restart PowerShell

---

## Step 5: Verify Firebase is Connected

Once you've set env vars and started the app:

```bash
cd /Users/wizo/Yammay_Blacksmith/artisan-iron-web
./mvnw spring-boot:run
```

Look for this in the console output:
```
... Initializing Firebase with service account JSON
... Firebase Realtime Database initialized: https://artisan-ion-default-rtdb.firebaseio.com
```

**If you see an error**, check:
- [ ] `FIREBASE_DATABASE_URL` is set and correct
- [ ] `FIREBASE_SERVICE_ACCOUNT_PATH` points to a real file
- [ ] The JSON file has not been modified
- [ ] Realtime Database is enabled in Firebase console (check the "Database" page)

---

## Step 6: Check Data in Firebase Console

After the app starts, you can watch data appear in Firebase:

1. Go to Firebase console → **Realtime Database**
2. You should see an empty JSON tree `{ }`
3. Submit a contact form on http://localhost:8080/contact
4. Refresh the Firebase console
5. You'll see the message appear:
   ```json
   {
     "contactMessages": {
       "-NxAbc123XyZ": {
         "id": "-NxAbc123XyZ",
         "senderName": "John Doe",
         "email": "john@example.com",
         "message": "Hello!",
         ...
       }
     }
   }
   ```

This confirms Firebase is working!

---

## Troubleshooting Firebase Issues

### Error: "Failed to initialize FirebaseDatabase"

**Cause:** Service account JSON file not found or env var not set

**Fix:**
```bash
# Check the file exists
ls -la ~/firebase-keys/artisan-iron-firebase.json

# Check env var is set
echo $FIREBASE_SERVICE_ACCOUNT_PATH

# Re-export if needed
export FIREBASE_SERVICE_ACCOUNT_PATH=~/firebase-keys/artisan-iron-firebase.json
```

### Error: "PERMISSION_DENIED"

**Cause:** Realtime Database rules are too strict

**Fix:**
1. Go to Firebase console → **Realtime Database**
2. Click **Rules** tab
3. Replace with Test Mode rules:
   ```json
   {
     "rules": {
       ".read": true,
       ".write": true
     }
   }
   ```
4. Click **Publish**

**WARNING:** This allows anyone to read/write. For production, we'll set proper rules before deployment.

### Error: "Connection timeout"

**Cause:** Firewall blocking Firebase or internet down

**Fix:**
- Check internet connection: `ping google.com`
- Check Firebase console is accessible: Open https://console.firebase.google.com
- If behind corporate firewall, ask IT to allow `*.firebaseio.com` and `*.firebase.google.com`

---

## Security Notes for Production

Before deploying to production (Railway/Render):

1. **Never commit the service account JSON to Git**
   - Add to `.gitignore` (already done)
   - Use environment variables on production server

2. **Change Realtime Database Rules**
   - Go to Firebase console → Rules tab
   - Replace Test Mode rules with:
     ```json
     {
       "rules": {
         "products": {
           ".read": true,
           ".write": false
         },
         "categories": {
           ".read": true,
           ".write": false
         },
         "contactMessages": {
           ".read": false,
           ".write": false
         },
         "inquiries": {
           ".read": false,
           ".write": false
         }
       }
     }
     ```
   - This allows public to read products, but only the backend can write

3. **On Railway/Render**
   - Set env vars in the platform dashboard (never hardcode)
   - Use the production service account key

---

## Testing Firebase Connection

After setup, test that everything works:

1. **Start the app:**
   ```bash
   cd /Users/wizo/Yammay_Blacksmith/artisan-iron-web
   ./mvnw spring-boot:run
   ```

2. **Visit:** http://localhost:8080

3. **Submit a contact form**

4. **Check Firebase console for the data**

5. **Try admin panel:** http://localhost:8080/admin
   - Add a product with an image
   - Verify it appears in gallery
   - Check Firebase console for the product data

If all that works, Firebase is properly configured! ✅

---

## What's Next?

- Read `QUICK_START.md` to run the app
- Read `LOCAL_SETUP.md` for full development guide
- Read `README.md` for deployment instructions
