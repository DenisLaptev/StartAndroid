package com.a5androidintern2.p0321_simplebrowser;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class BrowserActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        //определяем WebView
        webView = (WebView) findViewById(R.id.browser_webView);

        //читаем data з интента.
        Uri data = getIntent().getData();

        //передаём строку в WebView.
        webView.loadUrl(data.toString());
    }
}
