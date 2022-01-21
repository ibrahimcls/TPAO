package com.ugur.browser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserActivity extends AppCompatActivity {

    final AppCompatActivity activity = this;
    public final static String URL = "https://www.google.com/";

    WebView webview;

    WebChromeClient webChromeClient = new WebChromeClient() {
        public void onProgressChanged(WebView view, int progress) {
            activity.setTitle("Loading...");
            activity.setProgress(progress * 100);
            if (progress == 100)
                activity.setTitle(getResources().getString(R.string.app_name));
        }
    };

    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        webview = findViewById(R.id.webview);
        webview.setWebChromeClient(webChromeClient);
        webview.setWebViewClient(webViewClient);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(URL);
    }


    @Override
    public void onBackPressed() {
        if (webview.canGoBack())
            webview.goBack();
        else{
            new AlertDialog.Builder(this)
                    .setMessage("Cıkış yapmak istediğinize emin misiniz ?")
                    .setCancelable(false)
                    .setNegativeButton("Hayır", (dialog, which) -> dialog.dismiss())
                    .setPositiveButton("Evet", (dialog, which) -> finish()).show();
        }
    }
}