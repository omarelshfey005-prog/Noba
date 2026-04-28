# NubaFarm System - Android App | تطبيق نوبا فارم - أندرويد

## 📱 نظرة عامة | Overview

تحويل كامل لموقع NubaFarm إلى تطبيق Android أصلي مع الحفاظ على جميع الميزات والتصميم والألوان بدون أي تغيير.

**Complete conversion of NubaFarm website to a native Android application while maintaining all features, design, and colors without any changes.**

---

## 🚀 البدء السريع | Quick Start

### المتطلبات | Requirements:
- Android Studio 2021.3 أو أحدث
- JDK 11 أو أحدث
- SDK Android 21+ (الحد الأدنى)
- 2GB RAM على الأقل

### خطوات التثبيت | Installation Steps:

#### 1️⃣ فتح المشروع | Open Project
```bash
# في Android Studio:
File → Open → اختر مجلد Android
```

#### 2️⃣ مزامنة Gradle | Sync Gradle
```
File → Sync Now
```

#### 3️⃣ تشغيل التطبيق | Run App
```
Run → Run 'app' (أو اضغط Shift+F10)
```

---

## 📁 هيكل المشروع | Project Structure

```
Android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/nubafarm/system/
│   │   │   │   └── MainActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   └── values/
│   │   │   │       ├── strings.xml
│   │   │   │       ├── colors.xml
│   │   │   │       └── styles.xml
│   │   │   ├── assets/
│   │   │   │   └── index.html (الموقع كاملاً)
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── gradle.properties
```

---

## 🔧 المزايا الرئيسية | Key Features

✅ **نفس التصميم بالضبط** - Identical Design
- جميع الألوان والخطوط والتخطيط الأصلي
- All original colors, fonts, and layout

✅ **وظائف كاملة** - Full Functionality
- إدارة الإنتاج والبالتات
- شحن وتتبع الشحنات
- طباعة الملصقات الحرارية
- أرقام التتبع والرموز الشريطية

✅ **دعم اللغات** - Multi-Language Support
- الإنجليزية والعربية
- تبديل سلس بين اللغات

✅ **تخزين محلي** - Local Storage
- حفظ البيانات محلياً في الجهاز
- لا تحتاج اتصال إنترنت دائم

✅ **طباعة محسّنة** - Optimized Printing
- دعم كامل للطابعات الحرارية
- جودة طباعة عالية

---

## 🔐 الأذونات المستخدمة | Permissions Used

```xml
<!-- Manifest Permissions -->
- android.permission.INTERNET
- android.permission.ACCESS_FINE_LOCATION
- android.permission.ACCESS_COARSE_LOCATION
- android.permission.WRITE_EXTERNAL_STORAGE
- android.permission.READ_EXTERNAL_STORAGE
```

---

## 📊 إحصائيات التطبيق | App Statistics

| المعيار | القيمة |
|--------|--------|
| الحد الأدنى SDK | 21 (Android 5.0) |
| SDK المستهدف | 34 (Android 14) |
| حجم التطبيق | ~15-20 MB |
| إصدار التطبيق | 2.1 |
| حزمة التطبيق | com.nubafarm.system |

---

## 🛠️ إعدادات الإصدار | Build Configuration

### Debug Build (للاختبار)
```bash
./gradlew assembleDebug
```

### Release Build (للنشر)
```bash
./gradlew assembleRelease
```

---

## 📝 ملاحظات تقنية | Technical Notes

### WebView Configuration:
- JavaScript مفعل ✓
- DOM Storage مفعل ✓
- Local Storage مفعل ✓
- Zoom Support مفعل ✓
- Wide Viewport مفعل ✓
- Mixed Content مفعل ✓

### Performance:
- استخدام ProGuard للإصدارات
- تحسين الذاكرة
- دعم Android 5.0+ إلى Android 14

---

## 🌐 دعم الإنترنت | Internet Support

التطبيق يعمل بدون إنترنت، لكن المزايا التالية تحتاج اتصال:
- تحميل الخطوط من Google Fonts
- تحميل أيقونات Font Awesome
- مكتبة JsBarcode للرموز الشريطية

---

## 🎨 تخصيص الألوان | Customization

يمكنك تعديل الألوان من `res/values/colors.xml`:

```xml
<color name="primary">#ea580c</color>
<color name="primary_dark">#c63f09</color>
<color name="bg_light">#f8fafc</color>
<!-- إلخ -->
```

---

## 🐛 استكشاف الأخطاء | Troubleshooting

### المشكلة: التطبيق لا يحمل
**الحل:**
```
1. تأكد من اتصال الإنترنت
2. امسح Cache: Build → Clean Project
3. أعد بناء: Build → Rebuild Project
```

### المشكلة: الطباعة لا تعمل
**الحل:**
```
1. تحقق من أذونات الطابعة
2. اختبر بطابعة مختلفة
3. تحقق من إعدادات WebView
```

### المشكلة: لا يتم حفظ البيانات
**الحل:**
```
1. تحقق من أذونات التخزين
2. امنح صلاحيات التطبيق
3. تأكد من وجود مساحة كافية
```

---

## 📦 النشر على Google Play Store | Publishing

### 1️⃣ إنشاء مفتاح التوقيع | Create Signing Key
```bash
keytool -genkey -v -keystore nubafarm.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias nubafarm
```

### 2️⃣ توقيع Release Build
```bash
./gradlew assembleRelease
```

### 3️⃣ تحميل على Google Play Console
- اذهب إلى https://play.google.com/console
- أنشئ تطبيق جديد
- أضف البيانات
- حمّل الـ Release APK/AAB

---

## 👨‍💻 المطور | Developer

**Omar Elshafey**
- البريد: support@nubafarm.com
- الموقع: https://nubafarm.com

---

## 📄 الترخيص | License

هذا التطبيق حقوق محفوظة لـ NubaFarm System 2026.

**All Rights Reserved © 2026 NubaFarm System**

---

## 🎯 الإصدار الحالي | Current Version

- **الإصدار:** 2.1
- **التاريخ:** 28 أبريل 2026
- **الحالة:** إنتاج
- **النوع:** تطبيق أصلي Android

---

## 📞 الدعم | Support

للمساعدة والدعم الفني:
- 📧 البريد: support@nubafarm.com
- 🌐 الموقع: https://nubafarm.com
- ☎️ الهاتف: +20 100 123 4567

---

**تم تطوير التطبيق بنجاح ✓**
**Application Successfully Developed ✓**
