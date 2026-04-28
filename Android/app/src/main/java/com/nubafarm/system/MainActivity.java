package com.nubafarm.system;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        
        // Configure WebView settings
        WebSettings settings = webView.getSettings();
        
        // Enable JavaScript
        settings.setJavaScriptEnabled(true);
        
        // Enable DOM storage and local storage
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLocalStorageEnabled(true);
        settings.setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        settings.setAppCacheEnabled(true);
        
        // Enable zoom
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(true);
        settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        
        // Set viewport
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        
        // File access
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        
        // Mixed content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        
        // User agent
        settings.setUserAgentString(settings.getUserAgentString() + " NubaFarm/2.1");
        
        // Set WebViewClient to prevent default browser opening
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }
        });
        
        // Load local HTML file
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
