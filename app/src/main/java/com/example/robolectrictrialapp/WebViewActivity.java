package com.example.robolectrictrialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //MainActivityからデータを受け取る
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        //URLを開く
        WebView webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        //WebViewのJavaScriptを有効へ
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //WebViewの進捗を表示
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            //読み込み開始時にプログレスバー表示
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            //読み込み完了時にプログレスバー非表示
            progressBar.setVisibility(View.GONE);
        }
    }

    protected class MyWebChromeClient extends WebChromeClient {
        public void onProgressChanged(WebView view, int progress) {

            //プログレスバーの進捗を更新
            progressBar.setProgress(progress);
        }
    }

}