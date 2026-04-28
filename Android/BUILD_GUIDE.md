# 🔨 دليل البناء | Build Guide

## البناء والتجميع الكامل | Complete Build & Compilation Guide

---

## 📋 المتطلبات المسبقة | Prerequisites

### المتطلبات الإلزامية:
```
✓ Java JDK 11 أو أحدث
✓ Android SDK (مثبت عبر Android Studio أو sdkmanager)
✓ Gradle 8.1.2 أو أحدث (يُثبت تلقائياً)
✓ 10 GB مساحة حرة على الأقل
```

### المتطلبات الاختيارية:
```
- Android Studio (الموصى به للتطوير)
- Git (للتحكم بالإصدار)
- Maven (لإدارة التبعيات)
```

---

## 🛠️ إعداد البيئة | Environment Setup

### تثبيت Android SDK (Windows):

#### الطريقة 1: عبر Android Studio
```
1. حمّل Android Studio من android.com
2. افتح Android Studio
3. Tools → SDK Manager
4. ثبّت:
   - Android SDK Platform (API 34)
   - Android SDK Platform (API 21)
   - Android SDK Tools
   - Build Tools 34.0.0
```

#### الطريقة 2: عبر Command Line
```powershell
# تحميل sdkmanager
# ثم قم بتشغيل:
sdkmanager "platforms;android-34"
sdkmanager "platforms;android-21"
sdkmanager "build-tools;34.0.0"
sdkmanager "tools"
```

### إعداد متغيرات البيئة:

#### Windows:
```powershell
# 1. افتح Environment Variables
# Press Windows + R
# اكتب: sysdm.cpl

# 2. اضغط Advanced Tab
# 3. اضغط Environment Variables

# 4. أضف JAVA_HOME:
JAVA_HOME = C:\Program Files\Java\jdk-11
# أو المسار الصحيح لديك

# 5. أضف ANDROID_HOME:
ANDROID_HOME = C:\Users\{اسمك}\AppData\Local\Android\Sdk

# 6. أضف إلى PATH:
%JAVA_HOME%\bin
%ANDROID_HOME%\tools
%ANDROID_HOME%\platform-tools
```

#### التحقق من الإعداد:
```powershell
# افتح PowerShell وتحقق:
java -version
# يجب أن يعرض Java 11+

gradle -v
# يجب أن يعرض Gradle 8.1.2+

adb version
# يجب أن يعرض إصدار ADB
```

---

## 📂 استنساخ/فتح المشروع | Clone/Open Project

### إذا كنت تستخدم Git:
```bash
git clone https://github.com/nubafarm/android-app.git
cd android-app
```

### إذا كان لديك ملف ZIP:
```bash
# فك الضغط عن الملف
# انتقل إلى المجلد:
cd Android
```

### فتح في Android Studio:
```
1. افتح Android Studio
2. File → Open
3. اختر مجلد Android
4. اضغط OK
5. انتظر المزامعة
```

---

## 🔗 مزامعة Gradle | Gradle Sync

### عبر Android Studio:
```
File → Sync Now
أو Ctrl+Shift+O
```

### عبر Command Line:
```bash
# في مجلد المشروع
./gradlew sync
```

### انتظر:
```
⏳ يستغرق 2-5 دقائق
✓ سيحمل التبعيات تلقائياً
✓ سيأخذ مساحة 1-2 GB
```

---

## 🔨 بناء التطبيق | Building the App

### 1️⃣ بناء Debug APK (سريع للاختبار)

#### عبر Android Studio:
```
Build → Build Bundle(s) / APK(s) → Build APK
```

#### عبر Command Line:
```bash
./gradlew assembleDebug
```

**النتيجة:**
```
app/build/outputs/apk/debug/app-debug.apk
```

### 2️⃣ بناء Release APK (للإنتاج)

#### عبر Command Line:
```bash
./gradlew assembleRelease
```

**النتيجة:**
```
app/build/outputs/apk/release/app-release-unsigned.apk
```

### 3️⃣ بناء AAB (Android App Bundle)

#### للنشر على Google Play:
```bash
./gradlew bundleRelease
```

**النتيجة:**
```
app/build/outputs/bundle/release/app-release.aab
```

---

## 🔐 توقيع الـ Release | Signing Release APK

### إنشاء مفتاح التوقيع (Keystore):

#### أول مرة:
```bash
keytool -genkey -v -keystore nubafarm.keystore ^
    -keyalg RSA -keysize 2048 -validity 10000 ^
    -alias nubafarm
```

**سيطلب منك:**
```
Enter keystore password: [أدخل كلمة مرور قوية]
Re-enter password: [أدخل نفسها]
First and last name: [اسمك]
Organization unit: [قسمك]
Organization: [شركتك]
City: [المدينة]
State: [الولاية]
Country code: [eg]
```

### توقيع ملف APK:

```bash
jarsigner -verbose -sigalg SHA256withRSA ^
    -digestalg SHA-256 ^
    -keystore nubafarm.keystore ^
    app/build/outputs/apk/release/app-release-unsigned.apk ^
    nubafarm
```

### التحقق من التوقيع:

```bash
jarsigner -verify -verbose -certs ^
    app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## ✅ التحقق من البناء | Verify Build

### فحص حجم الملف:
```bash
# يجب أن يكون بين 15-20 MB
dir app\build\outputs\apk\debug\app-debug.apk
```

### عرض محتويات APK:
```bash
# APK هو ملف ZIP
7z l app/build/outputs/apk/debug/app-debug.apk
```

### التحقق من الموارد:
```bash
# تحقق من وجود:
# - assets/index.html
# - res/drawable/
# - res/values/
```

---

## 📱 اختبار التطبيق | Testing the App

### على المحاكي:

#### تشغيل المحاكي:
```bash
emulator -avd Pixel_5_API_30
```

#### تثبيت APK:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

#### تشغيل التطبيق:
```bash
adb shell am start -n com.nubafarm.system/.MainActivity
```

### على جهاز حقيقي:

#### 1. تفعيل USB Debugging:
```
Settings → About Phone → Build Number (اضغط 7 مرات)
Settings → Developer Options → USB Debugging (فعّل)
```

#### 2. توصيل الجهاز:
```bash
# تحقق من التوصيل:
adb devices
```

#### 3. تثبيت التطبيق:
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

#### 4. مراقبة السجلات:
```bash
adb logcat | grep nubafarm
```

---

## 🧹 تنظيف البناء | Clean Build

### حذف ملفات البناء السابقة:

```bash
./gradlew clean
```

### بناء من الصفر:
```bash
./gradlew clean assembleDebug
```

### تفريغ الذاكرة:
```bash
./gradlew --stop
```

---

## 🔍 حل مشاكل البناء | Troubleshooting Build Issues

### المشكلة: "SDK not found"

**الحل:**
```bash
# تحقق من ANDROID_HOME
echo %ANDROID_HOME%

# إذا لم يكن صحيحاً، قم بتعيينه:
set ANDROID_HOME=C:\path\to\android\sdk
```

### المشكلة: "Build failed"

**الحل:**
```bash
# 1. نظّف البناء
./gradlew clean

# 2. مزامعة من جديد
./gradlew sync

# 3. ابنِ من جديد
./gradlew assembleDebug
```

### المشكلة: "OutOfMemoryError"

**الحل:**
```bash
# زد الذاكرة في gradle.properties:
# قم بتعديل:
org.gradle.jvmargs=-Xmx4096m -XX:MaxPermSize=512m
```

### المشكلة: "JAVA_HOME not set"

**الحل:**
```powershell
# تحقق من الإعداد
echo $env:JAVA_HOME

# إذا كان فارغاً:
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11"
```

---

## 📊 خيارات البناء المتقدمة | Advanced Build Options

### بناء مع ProGuard:
```bash
# تفعيل minification في release:
./gradlew assembleRelease -PmyMinify
```

### بناء بدون اختبارات:
```bash
./gradlew assemble -x test
```

### بناء ببطء مع verbose:
```bash
./gradlew assembleDebug --info
```

### بناء متوازي (أسرع):
```bash
./gradlew assembleDebug -x test --parallel
```

---

## 🚀 النشر على Google Play | Publishing to Google Play

### 1️⃣ التحضير:

```
1. إنشاء حساب Google Play Developer
2. دفع رسم التسجيل ($25)
3. إنشاء تطبيق جديد
```

### 2️⃣ إنشاء ملف keystore:

```bash
# (تم شرحه أعلاه)
keytool -genkey -v -keystore nubafarm.keystore ...
```

### 3️⃣ بناء وتوقيع Release:

```bash
# 1. بناء AAB (يفضل من APK)
./gradlew bundleRelease

# 2. سيكون في:
app/build/outputs/bundle/release/app-release.aab
```

### 4️⃣ التحميل:

```
1. Google Play Console → تطبيقك
2. اضغط "Create new release"
3. اختر "Internal testing" أو "Production"
4. ارفع ملف AAB
5. أضف وصفاً والصور
6. اضغط "Review release"
7. اضغط "Release to production"
```

---

## 📦 ملفات البناء الرئيسية | Key Build Files

### build.gradle (app level):
```gradle
android {
    compileSdk 34
    
    defaultConfig {
        applicationId "com.nubafarm.system"
        minSdk 21
        targetSdk 34
        versionCode 1
        versionName "2.1.0"
    }
}

dependencies {
    // التبعيات هنا
}
```

### gradle.properties:
```
org.gradle.jvmargs=-Xmx2048m
android.useAndroidX=true
android.enableJetifier=true
```

### proguard-rules.pro:
```
-keep class com.nubafarm.system.** { *; }
-keep class android.webkit.WebView { *; }
```

---

## 📊 رصد الأداء | Performance Monitoring

### حجم APK:
```bash
# يجب أن يكون < 20 MB
```

### وقت البناء:
```bash
./gradlew assembleDebug --profile
# انظر إلى build/reports/profile
```

### استهلاك الذاكرة:
```bash
# أثناء البناء:
Task monitor في Windows
```

---

## ✅ قائمة تحقق البناء | Build Checklist

```
☐ Java JDK 11+ مثبت
☐ Android SDK مثبت
☐ متغيرات البيئة معينة
☐ المشروع مستنسخ
☐ Gradle مزامع
☐ لا توجد أخطاء في الكود
☐ البناء نجح
☐ حجم APK معقول (< 20 MB)
☐ التطبيق يعمل على المحاكي/الجهاز
☐ السجلات واضحة (لا أخطاء)
☐ جاهز للإطلاق! 🚀
```

---

## 🎓 أوامر Gradle المفيدة | Useful Gradle Commands

| الأمر | الوظيفة |
|------|--------|
| `./gradlew clean` | حذف البناء السابق |
| `./gradlew build` | بناء كامل |
| `./gradlew assembleDebug` | بناء Debug APK |
| `./gradlew assembleRelease` | بناء Release APK |
| `./gradlew test` | تشغيل الاختبارات |
| `./gradlew tasks` | عرض جميع الأمام |
| `./gradlew --version` | إصدار Gradle |
| `./gradlew --stop` | إيقاف Gradle daemon |

---

## 📞 الدعم والمساعدة | Support

### مشكلة في البناء؟
```
تحقق من:
1. إصدار Java الصحيح
2. Android SDK مثبت
3. متغيرات البيئة
4. مساحة حرة كافية
5. اتصال إنترنت
```

### تواصل:
```
📧 dev@nubafarm.com
💬 Slack: #android-dev
```

---

## 📚 مراجع إضافية | Additional Resources

- [Android Studio Build Guide](https://developer.android.com/build)
- [Gradle User Guide](https://docs.gradle.org)
- [Android SDK Documentation](https://developer.android.com)

---

**نجاح البناء! 🎉**

آخر تحديث: 28 أبريل 2026
