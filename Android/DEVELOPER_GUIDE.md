# 👨‍💻 دليل المطورين | Developer Guide

## 📚 نظرة عامة | Overview

هذا الدليل يساعد المطورين على فهم البنية والعمل على تطبيق NubaFarm Android.

---

## 🏗️ البنية المعمارية | Architecture

```
┌─────────────────────────────────────┐
│      Android Application            │
├─────────────────────────────────────┤
│      MainActivity (Java)            │
├─────────────────────────────────────┤
│      WebView Engine                 │
├─────────────────────────────────────┤
│      HTML/CSS/JavaScript (Web App)  │
├─────────────────────────────────────┤
│      LocalStorage (Data Persistence)│
└─────────────────────────────────────┘
```

---

## 📂 هيكل المشروع الكامل | Project Structure

```
Android/
│
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/nubafarm/system/
│   │       │   └── MainActivity.java (📱 Activity الرئيسي)
│   │       │
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   └── activity_main.xml (🎨 التخطيط)
│   │       │   │
│   │       │   └── values/
│   │       │       ├── strings.xml (📝 النصوص والترجمات)
│   │       │       ├── colors.xml (🎨 الألوان)
│   │       │       └── styles.xml (✨ الأنماط)
│   │       │
│   │       ├── assets/
│   │       │   └── index.html (🌐 التطبيق الويب)
│   │       │
│   │       └── AndroidManifest.xml (⚙️ الإعدادات)
│   │
│   ├── build.gradle (🔨 بناء التطبيق)
│   └── proguard-rules.pro (🔒 حماية الكود)
│
├── build.gradle (🔧 إعدادات Gradle الرئيسية)
├── settings.gradle (📋 إعدادات المشروع)
├── gradle.properties (⚡ خصائص Gradle)
│
├── README.md (📖 التوثيق الرئيسي)
├── INSTALLATION_GUIDE.md (📲 دليل التثبيت)
└── DEVELOPER_GUIDE.md (👨‍💻 هذا الملف)
```

---

## 🔴 MainActivity.java - الشرح التفصيلي | Detailed Explanation

```java
public class MainActivity extends AppCompatActivity {
    private WebView webView;  // متصفح الويب المدمج

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webView = findViewById(R.id.webView);
        
        // ⚙️ إعدادات WebView
        WebSettings settings = webView.getSettings();
        
        // 🔓 تفعيل JavaScript
        settings.setJavaScriptEnabled(true);
        
        // 💾 تفعيل التخزين المحلي
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLocalStorageEnabled(true);
        
        // 🔧 إعدادات أخرى...
        
        // 📄 تحميل ملف HTML
        webView.loadUrl("file:///android_asset/index.html");
    }
    
    // ⬅️ معالجة زر الرجوع
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
```

---

## 🎨 activity_main.xml - التخطيط | Layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout>
    <!-- المتصفح الذي يعرض الموقع -->
    <WebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>
```

**ملاحظة:** التخطيط بسيط جداً لأن الواجهة كاملة في HTML!

---

## 🌐 index.html - التطبيق الويب | Web App

الموقع الأصلي كاملاً مع:

✅ **جميع الميزات الأصلية**
- إدارة الإنتاج
- إدارة الشحنات
- طباعة الملصقات
- دعم اللغات

✅ **تحسينات للجوال**
- responsive design
- تخطيط محسّن للشاشات الصغيرة
- دعم اللمس

---

## 🔐 AndroidManifest.xml - الإعدادات | Settings

### الأذونات الرئيسية:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

### Activity الرئيسي:
```xml
<activity
    android:name=".MainActivity"
    android:exported="true"
    android:screenOrientation="portrait"
    android:configChanges="orientation|screenSize|keyboardHidden">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

---

## 🔧 بناء وتجميع | Building & Compiling

### 1️⃣ بناء Debug APK
```bash
./gradlew assembleDebug
# النتيجة: app/build/outputs/apk/debug/app-debug.apk
```

### 2️⃣ بناء Release APK
```bash
./gradlew assembleRelease
# النتيجة: app/build/outputs/apk/release/app-release-unsigned.apk
```

### 3️⃣ توقيع Release APK
```bash
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 \
    -keystore nubafarm.keystore \
    app-release-unsigned.apk nubafarm
```

---

## 🚀 التطوير والاختبار | Development & Testing

### تفعيل USB Debugging على الجهاز:
```
Settings → About Phone → Build Number (اضغط 7 مرات)
Settings → Developer Options → USB Debugging ✓
```

### أوامر مفيدة:
```bash
# عرض الأجهزة المتصلة
adb devices

# تثبيت التطبيق
adb install app/build/outputs/apk/debug/app-debug.apk

# عرض السجلات
adb logcat

# عرض سجلات التطبيق فقط
adb logcat | grep nubafarm

# حذف البيانات
adb shell pm clear com.nubafarm.system

# إعادة تشغيل
adb shell am start -n com.nubafarm.system/.MainActivity
```

---

## 🔍 تخصيص التطبيق | Customization

### تغيير الألوان:
```xml
<!-- res/values/colors.xml -->
<color name="primary">#ea580c</color>  <!-- البرتقالي الرئيسي -->
<color name="primary_dark">#c63f09</color>
```

### تغيير النصوص:
```xml
<!-- res/values/strings.xml -->
<string name="app_name">NubaFarm System</string>
<string name="loading">Loading NubaFarm System...</string>
```

### تغيير الحجم الافتراضي:
```xml
<!-- res/values/styles.xml -->
<item name="android:textSize">16sp</item>
```

---

## 📱 المحاكي (Emulator) | Emulator Setup

### إنشاء محاكي:
```
Android Studio → AVD Manager → Create Virtual Device

الإعدادات الموصى بها:
- Device: Pixel 5
- API Level: 30+
- RAM: 2GB
- Storage: 50GB
```

### تشغيل المحاكي:
```bash
# عرض قائمة المحاكيات
emulator -list-avds

# تشغيل محاكي
emulator -avd Pixel_5_API_30
```

---

## 🐛 تصحيح الأخطاء | Debugging

### استخدام Logcat:
```java
Log.d("NubaFarm", "Debug message: " + variable);
Log.e("NubaFarm", "Error: ", exception);
Log.i("NubaFarm", "Info message");
```

### مراقبة الموارد:
```bash
# مراقبة استهلاك الذاكرة
adb shell dumpsys meminfo com.nubafarm.system

# مراقبة البطارية
adb shell dumpsys batterystats
```

---

## 📦 Gradle Dependencies | المكتبات المطلوبة

```gradle
dependencies {
    // AndroidX
    implementation 'androidx.appcompat:appcompat:1.6.1'
    
    // Material Design
    implementation 'com.google.android.material:material:1.11.0'
    
    // Testing
    testImplementation 'junit:junit:4.13.2'
}
```

---

## 🔒 ProGuard Rules | حماية الكود

```
# احتفظ بـ MainActivity
-keep class com.nubafarm.system.MainActivity { *; }

# احتفظ بـ JavaScript interfaces
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
```

---

## 🚀 النشر على Google Play | Publishing

### 1️⃣ إنشاء حساب Developer:
- اذهب إلى https://play.google.com/console
- ادفع رسم الاشتراك (25 دولار)
- أنشئ تطبيق جديد

### 2️⃣ إعداد التوقيع:
```bash
# إنشاء مفتاح التوقيع
keytool -genkey -v -keystore release.keystore \
    -keyalg RSA -keysize 2048 -validity 10000 \
    -alias nubafarm
```

### 3️⃣ تحضير Release:
```bash
# بناء Release APK
./gradlew assembleRelease

# توقيع APK
jarsigner -verbose -sigalg SHA256withRSA \
    -digestalg SHA-256 -keystore release.keystore \
    app/build/outputs/apk/release/app-release-unsigned.apk nubafarm
```

### 4️⃣ التحميل:
- Google Play Console → تطبيقك → ملف APK/AAB
- أضف الوصف والصور
- اضغط "نشر"

---

## 📊 تحسين الأداء | Performance Tips

```java
// ✓ استخدام View Binding
private ActivityMainBinding binding;

// ✓ تقليل تحميل JavaScript
webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

// ✓ تعطيل الميزات غير المستخدمة
webSettings.setPluginState(WebSettings.PluginState.OFF);

// ✓ استخدام ProGuard
minifyEnabled true
```

---

## 🎯 معايير الكود | Code Standards

### تسمية المتغيرات:
```java
private WebView webView;        // camelCase
private static final String TAG = "NubaFarm";  // UPPER_CASE
private Context mContext;       // m prefix للـ members
```

### التعليقات:
```java
// ✓ كود واضح بدون تعليقات معقدة
// ✓ تعليق لكل دالة معقدة
// ✓ تجنب التعليقات الواضحة
```

---

## 🤝 المساهمة | Contributing

### خطوات المساهمة:
1. Fork المشروع
2. أنشئ فرع جديد (`git checkout -b feature/xyz`)
3. اكتب الكود مع تعليقات
4. اختبر التطبيق
5. أرسل Pull Request

---

## 📞 للمساعدة | Getting Help

- 📧 البريد: dev@nubafarm.com
- 💬 Slack: #android-dev
- 📖 الوثائق: https://docs.nubafarm.com

---

## ✅ الخلاصة | Summary

**النقاط المهمة:**
1. ✓ WebView تحمل موقع الويب الكامل
2. ✓ localStorage يحفظ البيانات محلياً
3. ✓ JavaScript مفعل للتفاعل الكامل
4. ✓ Manifest يحدد الأذونات والإعدادات
5. ✓ Gradle يدير البناء والتبعيات

**للبدء:**
```bash
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
```

**شكراً لك! 🎉**

---

**آخر تحديث:** 28 أبريل 2026
**الإصدار:** 2.1
