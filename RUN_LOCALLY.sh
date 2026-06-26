#!/bin/bash

# Artisan Iron - Run Locally Script
# This script sets up environment variables and starts the app

echo "🔧 Artisan Iron - Local Development Setup"
echo "=========================================="
echo ""

# Step 1: Check Java
echo "✓ Checking Java..."
if ! command -v java &> /dev/null; then
    echo "❌ Java not found. Please install Java 17+"
    echo "   Download from: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html"
    exit 1
fi
java -version
echo ""

# Step 2: Check Maven
echo "✓ Checking Maven..."
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven not found. Please install Maven"
    echo "   Download from: https://maven.apache.org/download.cgi"
    exit 1
fi
mvn -version
echo ""

# Step 3: Ask for Firebase Setup
echo "📋 Firebase Configuration Required"
echo "====================================="
echo ""
echo "Have you completed the Firebase setup?"
echo "  1. Created Firebase project at https://console.firebase.google.com"
echo "  2. Enabled Realtime Database"
echo "  3. Downloaded service account JSON"
echo ""
read -p "Continue? (y/n) " -n 1 -r
echo
if [[ ! $REPLY =~ ^[Yy]$ ]]; then
    echo "Please complete Firebase setup first. See FIREBASE_SETUP.md"
    exit 1
fi
echo ""

# Step 4: Collect Configuration
echo "📝 Configuration"
echo "================="
echo ""

read -p "Enter Firebase Database URL (e.g., https://your-project-default-rtdb.firebaseio.com): " FIREBASE_DATABASE_URL
read -p "Enter path to Firebase service account JSON file: " FIREBASE_SERVICE_ACCOUNT_PATH
read -p "Enter SendGrid API key (or press Enter to skip email): " MAIL_PASSWORD
read -p "Enter admin password for /admin panel: " -s ADMIN_PASSWORD
echo ""
echo ""

# Step 5: Verify Files
if [ ! -f "$FIREBASE_SERVICE_ACCOUNT_PATH" ]; then
    echo "❌ Firebase JSON file not found at: $FIREBASE_SERVICE_ACCOUNT_PATH"
    exit 1
fi

echo "✓ Firebase JSON file found"
echo ""

# Step 6: Export Environment Variables
export FIREBASE_DATABASE_URL="$FIREBASE_DATABASE_URL"
export FIREBASE_SERVICE_ACCOUNT_PATH="$FIREBASE_SERVICE_ACCOUNT_PATH"
export MAIL_PASSWORD="$MAIL_PASSWORD"
export ADMIN_PASSWORD="$ADMIN_PASSWORD"

echo "✓ Environment variables set"
echo ""

# Step 7: Show Configuration
echo "📌 Configuration Summary"
echo "======================="
echo "FIREBASE_DATABASE_URL: $FIREBASE_DATABASE_URL"
echo "FIREBASE_SERVICE_ACCOUNT_PATH: $FIREBASE_SERVICE_ACCOUNT_PATH"
echo "MAIL_PASSWORD: $(if [ -z "$MAIL_PASSWORD" ]; then echo '(not set)'; else echo '(set)'; fi)"
echo "ADMIN_PASSWORD: (set)"
echo ""

# Step 8: Start App
echo "🚀 Starting Artisan Iron..."
echo "=============================="
echo ""
echo "When the app starts, you'll see:"
echo "  'Started ArtisanIronApplication in X.XXX seconds'"
echo ""
echo "Then open your browser to: http://localhost:8080"
echo ""
echo "To stop the app, press Ctrl+C"
echo ""
echo "Press Enter to start..."
read

# Download dependencies and start
cd "$(dirname "$0")"
./mvnw clean spring-boot:run
