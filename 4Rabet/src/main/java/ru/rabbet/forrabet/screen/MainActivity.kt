package ru.rabbet.forrabet.screen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.main.*
import ru.rabbet.forrabet.R
import ru.rabbet.forrabet.application.FourRabetApp

class MainActivity : AppCompatActivity() {

    val firebase = FirebaseRemoteConfig.getInstance()
    var currentUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        initWebView()
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            load()
        }
    }

    private fun getUrl(): String {
        return if (application is FourRabetApp) {
            (application as FourRabetApp).remoteConfig.getString("url")
        } else {
            getString(R.string.url)
        }
    }

    private fun initWebView() {
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                currentUrl = url
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipeRefresh.isRefreshing = false
            }
        }
        webView.clearCache(true)
        webView.clearHistory()
        webView.setInitialScale(1)
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        currentUrl = getUrl()
        load()
    }

    private fun load() {
        webView.loadUrl(currentUrl)
    }
}
