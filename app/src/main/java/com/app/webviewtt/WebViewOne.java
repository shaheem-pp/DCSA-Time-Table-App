package com.app.webviewtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewOne extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_one);

        getSupportActionBar().hide();

        final WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        progressBar = (ProgressBar) findViewById(R.id.webviewprogressbar);
        progressBar.setMax(100);


        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(false);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        Boolean syllabusPage = false;
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setEnableSmoothTransition(true);
        final String url = getIntent().getStringExtra("urltoload");

        syllabusPage = getIntent().getBooleanExtra("syllabusPage", false);


        if (url.equals("https://aquibe.github.io/e-sias-developers/")) {
            webView.setInitialScale(90);
        } else if (syllabusPage) {
            webView.setInitialScale(200);
        }
        else {
            webView.setInitialScale(100);
        }

        if (url.equals("https://aquibe.github.io/e-sias-magazine/index.html") || url.equals("https://aquibe.github.io/e-sias-publications/")) {
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webSettings.setAppCacheEnabled(false);
        }

        webView.loadUrl(url);
        final Boolean finalSyllabusPage = syllabusPage;
        final Boolean finalSyllabusPage1 = syllabusPage;

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (finalSyllabusPage1)
                    return false;

                if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                finish();
                startActivity(new Intent(WebViewOne.this,ErrorPage.class).putExtra("urltoload",url));
            }
        });


    }
}
