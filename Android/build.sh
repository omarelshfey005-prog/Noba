#!/bin/bash
# NubaFarm Android App - Build Script

echo "================================"
echo "NubaFarm System - Android App"
echo "================================"
echo ""

# Check if Gradle exists
if ! command -v gradle &> /dev/null; then
    echo "❌ Gradle is not installed!"
    echo "📝 Please install Gradle or use Android Studio"
    exit 1
fi

echo "🔍 Checking project structure..."

# Check required directories
if [ ! -d "app" ]; then
    echo "❌ app directory not found!"
    exit 1
fi

if [ ! -f "build.gradle" ]; then
    echo "❌ build.gradle not found!"
    exit 1
fi

echo "✓ Project structure verified"
echo ""

# Clean build
echo "🧹 Cleaning previous builds..."
./gradlew clean

# Sync Gradle
echo "🔗 Syncing Gradle..."
./gradlew sync

# Build debug APK
echo "🔨 Building Debug APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "✓ Build successful!"
    echo "📦 Output: app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "To run on device/emulator:"
    echo "  adb install -r app/build/outputs/apk/debug/app-debug.apk"
else
    echo "❌ Build failed!"
    exit 1
fi

echo "================================"
echo "✓ Build Complete"
echo "================================"
