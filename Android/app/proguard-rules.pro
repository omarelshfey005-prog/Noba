# ProGuard rules for NubaFarm System

# Keep all classes in the app
-keep class com.nubafarm.system.** { *; }

# Keep JavaScript interface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep WebView
-keep class android.webkit.WebView { *; }
-keep class android.webkit.WebViewClient { *; }
-keep class android.webkit.WebSettings { *; }

# Keep View constructors
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Keep R classes
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Enable optimization
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
