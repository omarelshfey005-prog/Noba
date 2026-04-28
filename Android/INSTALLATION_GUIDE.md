# 📱 تثبيت تطبيق NubaFarm على Android | NubaFarm Android App Installation

## 🎯 طرق التثبيت | Installation Methods

### الطريقة 1️⃣: من خلال Android Studio (للمطورين)

#### الخطوات:

1. **فتح المشروع**
```bash
File → Open → اختر مجلد Android
```

2. **مزامنة Gradle**
```
File → Sync Now
أو Ctrl+Shift+O
```

3. **تشغيل التطبيق**
```
Run → Run 'app'
أو اضغط Shift+F10
```

4. **اختيار الجهاز**
- اختر جهازك أو محاكي
- انتظر التثبيت

---

### الطريقة 2️⃣: من خلال ADB (Command Line)

#### المتطلبات:
- Android SDK Platform Tools
- جهاز Android متصل بـ USB
- وضع Developer Mode مفعل

#### الخطوات:

```bash
# 1. تفعيل USB Debugging
Settings → Developer Options → USB Debugging

# 2. توصيل الجهاز
adb devices

# 3. بناء التطبيق
./gradlew assembleDebug

# 4. تثبيت APK
adb install -r app/build/outputs/apk/debug/app-debug.apk

# 5. التحقق من التثبيت
adb shell pm list packages | grep nubafarm
```

---

### الطريقة 3️⃣: من ملف APK مباشرة

#### الخطوات:

1. **نسخ ملف APK إلى جهازك**
```
app/build/outputs/apk/debug/app-debug.apk
```

2. **تحويل إلى هاتفك**
- عبر USB
- عبر Bluetooth
- عبر البريد الإلكتروني

3. **تثبيت على الهاتف**
- افتح File Manager
- ابحث عن app-debug.apk
- اضغط Install
- اسمح بالأذونات

---

## 🔧 متطلبات النظام | System Requirements

| المتطلب | القيمة |
|--------|--------|
| نظام التشغيل | Android 5.0+ |
| الإصدار المثالي | Android 10+ |
| الذاكرة | 2 GB على الأقل |
| المساحة الحرة | 100 MB على الأقل |
| المعالج | ARM أو x86 |

---

## 🛡️ الأذونات المطلوبة | Required Permissions

عند التثبيت، سيطلب التطبيق المزايا التالية:

✓ **الإنترنت** - INTERNET
- لتحميل الخطوط والأيقونات

✓ **تحديد الموقع** - LOCATION
- للمزايا المستقبلية

✓ **التخزين** - STORAGE
- حفظ البيانات محلياً

---

## ✅ التحقق من التثبيت | Verify Installation

```bash
# التحقق من وجود التطبيق
adb shell pm list packages | grep nubafarm

# يجب أن يظهر:
# package:com.nubafarm.system
```

---

## 🚀 أول تشغيل | First Launch

### عند فتح التطبيق:

1. **الشاشة الترحيبية**
   - رسالة ترحيب
   - تحميل البيانات

2. **اختيار اللغة**
   - الإنجليزية (افتراضي)
   - العربية (عبر الزر)

3. **لوحة التحكم**
   - عرض الإحصائيات
   - الإجراءات السريعة

---

## 🐛 حل المشاكل الشائعة | Troubleshooting

### ❌ المشكلة: لا يمكن تثبيت التطبيق

**السبب:** إصدار Android قديم جداً
**الحل:** 
- تحديث Android إلى 5.0 على الأقل
- أو استخدام محاكي أحدث

### ❌ المشكلة: التطبيق يتعطل عند الفتح

**السبب:** نقص الذاكرة
**الحل:**
```bash
# حذف تطبيقات غير ضرورية
# أعد تشغيل الهاتف
# امسح ذاكرة التخزين المؤقتة
Settings → Apps → NubaFarm → Storage → Clear Cache
```

### ❌ المشكلة: البيانات لا تُحفظ

**السبب:** نقص صلاحيات التخزين
**الحل:**
```
Settings → Apps → NubaFarm → Permissions
تفعيل جميع الأذونات المطلوبة
```

### ❌ المشكلة: الطباعة لا تعمل

**السبب:** عدم توصيل الطابعة
**الحل:**
```
1. تأكد من توصيل الطابعة بنفس الشبكة
2. استخدم تطبيق الطابعة نفسه
3. جرّب إعادة تشغيل الطابعة
```

---

## 🔄 تحديث التطبيق | Updating App

### طريقة سهلة:

```bash
# بناء الإصدار الجديد
./gradlew assembleDebug

# إزالة القديم
adb uninstall com.nubafarm.system

# تثبيت الجديد
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

---

## 📊 معلومات مفيدة | Useful Info

### مواقع الملفات:

- **التطبيق الرئيسي:** `MainActivity.java`
- **التخطيط:** `activity_main.xml`
- **الموقع:** `assets/index.html`
- **الألوان:** `values/colors.xml`
- **النصوص:** `values/strings.xml`

### أوامر مفيدة:

```bash
# عرض السجلات
adb logcat | grep nubafarm

# مسح بيانات التطبيق
adb shell pm clear com.nubafarm.system

# إعادة تشغيل التطبيق
adb shell am start -n com.nubafarm.system/.MainActivity

# معلومات الجهاز
adb shell getprop ro.build.version.release
```

---

## 💾 النسخ الاحتياطي | Backup

### نسخ احتياطية من البيانات:

```bash
# نسخة احتياطية كاملة
adb backup -apk -all -f backup.ab

# استعادة النسخة
adb restore backup.ab
```

---

## 🎮 الاستخدام الأساسي | Basic Usage

### الشاشات الرئيسية:

1. **لوحة التحكم** 📊
   - الإحصائيات
   - الإجراءات السريعة

2. **الإنتاج** 📦
   - إنشاء بالتات
   - معاينة الملصقات
   - طباعة الملصقات

3. **المخزون** 📋
   - عرض البالتات
   - البحث
   - الطباعة

4. **الشحن** 🚢
   - إنشاء شحنات
   - اختيار البالتات
   - تتبع الشحنات

5. **الإعدادات** ⚙️
   - إدارة العملاء
   - تغيير الرمز السري
   - مسح البيانات

---

## 📞 الدعم | Support

للمساعدة:
- 📧 support@nubafarm.com
- 🌐 nubafarm.com
- ☎️ +20 100 123 4567

---

## ✓ تم التثبيت بنجاح! | Installation Complete!

الآن يمكنك البدء باستخدام تطبيق NubaFarm على هاتفك! 🎉

**You can now start using NubaFarm on your phone! 🎉**
