package com.easy.easynews.view

import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.easy.easynews.R

class WebViewActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // Intent
        val stUrl = intent.getStringExtra("url")

        // HOOKS
        webView = findViewById(R.id.web_view)
        toolbar = findViewById(R.id.toolbar)
        webView.visibility = View.GONE

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = CallBack()

        if (stUrl != null)
            webView.loadUrl(stUrl)

        Handler().postDelayed({
            webView.visibility = View.VISIBLE
        }, 1000)
    }

    private class CallBack : WebViewClient() {

        override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
            return false
        }
    }
}