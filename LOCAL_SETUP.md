# Local Development Setup - Artisan Iron

Follow these steps to run the app on your machine.

## Step 1: Prerequisites

Make sure you have installed:
- **Java 17 or higher** → Download from https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- **Maven** → Usually comes with Java, or download from https://maven.apache.org/download.cgi
- **Git** (optional, for version control)

### Verify Installation
```bash
java -version
# Should show Java 17+

mvn -version
# Should show Maven 3.6+
```

---

## Step 2: Set Up Firebase

### 2.1 Create Firebase Project
1. Go to https://console.firebase.google.com
2. Click "Add Project" or "Create Project"
3. Name it "artisan-iron" (or anything you prefer)
4. Click through the setup (default options are fine)
5. Wait for creation to finish

### 2.2 Enable Realtime Database
1. In Firebase console, left sidebar → "Build" → "Realtime Database"
2. Click "Create Database"
3. Choose location (closest to you)
4. **IMPORTANT:** Start in **Test Mode** (allows public read/write for development)
5. Click "Enable"

### 2.3 Download Service Account JSON
1. Click the gear icon (⚙️) in Firebase console → "Project Settings"
2. Go to "Service Accounts" tab
3. Click "Generate New Private Key"
4. Save the JSON file to your computer
   - **Example path:** `~/Downloads/artisan-iron-firebase.json`
5. **IMPORTANT:** Keep this file private! Never commit to Git.

### 2.4 Find Your Database URL
1. Go to Realtime Database page
2. Copy the URL shown at top (looks like: `https://your-project-default-rtdb.firebaseio.com`)

---

## Step 3: Set Up SendGrid for Email (Optional for Local Testing)

If you want email to work locally:

1. Go to https://sendgrid.com
2. Create a free account
3. Get your API Key (Settings → API Keys → Create API Key)
4. Copy the key

**If you skip this:** Emails will fail silently, but the app will still work.

---

## Step 4: Configure Environment Variables

### Option A: Terminal (Recommended for quick testing)

**Mac/Linux:**
```bash
export FIREBASE_DATABASE_URL=https://your-project-default-rtdb.firebaseio.com
export FIREBASE_SERVICE_ACCOUNT_PATH=~/Downloads/artisan-iron-firebase.json
export MAIL_PASSWORD=your-sendgrid-api-key-here
export ADMIN_PASSWORD=mypassword123
```

**Windows (PowerShell):**
```powershell
$env:FIREBASE_DATABASE_URL="https://your-project-default-rtdb.firebaseio.com"
$env:FIREBASE_SERVICE_ACCOUNT_PATH="C:\Users\YourName\Downloads\artisan-iron-firebase.json"
$env:MAIL_PASSWORD="your-sendgrid-api-key-here"
$env:ADMIN_PASSWORD="mypassword123"
```

### Option B: Create `.env.local` file (Alternative)

Create a file `artisan-iron-web/.env.local`:
```
FIREBASE_DATABASE_URL=https://your-project-default-rtdb.firebaseio.com
FIREBASE_SERVICE_ACCOUNT_PATH=/Users/your-username/Downloads/artisan-ion-firebase.json
MAIL_PASSWORD=your-sendgrid-api-key
ADMIN_PASSWORD=mypassword123
```

**Note:** Spring Boot doesn't automatically load .env files, so use Option A for now.

---

## Step 5: Run the Application

### 5.1 Navigate to Project Directory
```bash
cd /Users/wizo/Yammay_Blacksmith/artisan-iron-web
```

### 5.2 Download Dependencies (First time only)
```bash
./mvnw clean
```
This downloads all Maven dependencies. Takes 2-3 minutes first time.

### 5.3 Run the App
```bash
./mvnw spring-boot:run
```

You should see output like:
```
...
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_|\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 ...
 Started ArtisanIronApplication in 5.123 seconds
```

✅ **App is running!**

---

## Step 6: Access the App

Open your browser and go to:
```
http://localhost:8080
```

### Test These Pages:
- **Home:** http://localhost:8080/
- **Gallery:** http://localhost:8080/gallery
- **About:** http://localhost:8080/about
- **Services:** http://localhost:8080/services
- **Contact:** http://localhost:8080/contact
- **Inquiry:** http://localhost:8080/inquiry
- **Admin:** http://localhost:8080/admin
  - Username: `admin`
  - Password: (whatever you set in `ADMIN_PASSWORD`)

---

## Step 7: Seed Sample Data (Optional)

The app comes with no products. To add sample products to Firebase:

### Create a Product in Admin Panel:
1. Login to http://localhost:8080/admin (username: admin, password: as set)
2. Click "Add New Product"
3. Fill in:
   - **URL Slug:** `gate-pompiya`
   - **Name:** `שער ברזל פומפיה` (or English: `Iron Gate Pompiya`)
   - **Description:** `שער מעוצב בעבודת יד` (Handcrafted iron gate)
   - **Materials:** `ברזל מחוזק, עיצוב ידני`
   - **Price:** `8500`
   - **Category:** `gates`
   - **Image:** Pick any image from your computer (it will be compressed & Base64 encoded)
   - **Featured:** Check this box
4. Click "Save"

Now visit http://localhost:8080/gallery and you'll see the product!

---

## Step 8: Troubleshooting

### "Port 8080 already in use"
```bash
# On Mac/Linux, find what's using port 8080:
lsof -i :8080

# Kill it:
kill -9 <PID>

# Or change port in application.yml:
server.port=8081
```

### "Firebase connection failed"
- Double-check `FIREBASE_DATABASE_URL` is correct (copy-paste from Firebase console)
- Verify service account JSON file path is correct
- Make sure Realtime Database is **enabled** in Firebase console

### "Email not working"
- If `MAIL_PASSWORD` is not set, emails will fail silently (app still works)
- Contact form submissions will save to Firebase even if email fails

### "Admin login not working"
- Username is always: `admin`
- Password is whatever you set in `ADMIN_PASSWORD` env var
- Password is BCrypt hashed, so plain text env var is correct

---

## Step 9: Stop the App

Press **Ctrl+C** in the terminal where it's running.

---

## Step 10: Make Changes & Reload

With `spring-boot-devtools` enabled:
- **Java files:** Auto-restarts the app when you save
- **Templates (HTML):** Auto-refreshes when you reload browser
- **CSS/JS:** Manual browser refresh (Ctrl+Shift+R)

So you can edit code and just refresh the browser!

---

## Local Development Workflow

1. **Start the app:** `./mvnw spring-boot:run`
2. **Make changes** to Java, HTML, or CSS
3. **Reload browser** to see changes
4. **Check Firebase console** to verify data is saved
5. **Test forms** to make sure email works
6. **Test admin panel** to add/delete products

---

## Next: Deployment

Once everything works locally, see `README.md` for deployment to Railway or Render.
