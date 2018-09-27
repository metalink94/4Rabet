package ru.rabbet.forrabet.screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.main.*
import ru.rabbet.forrabet.BuildConfig
import ru.rabbet.forrabet.R
import ru.rabbet.forrabet.application.FourRabetApp

class MainActivity : AppCompatActivity() {

    val firebase = FirebaseRemoteConfig.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        initWebView()
    }

    private fun getUrl(): String {
        return if (application is FourRabetApp) {
            if (BuildConfig.DEBUG) {
                (application as FourRabetApp).remoteConfig.getString("url_test")
            } else {
                (application as FourRabetApp).remoteConfig.getString("url")
            }
        } else {
            getString(R.string.url)
        }
    }

    private fun initWebView() {
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                return if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    view.context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    true
                } else {
                    false
                }
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
        webView.loadUrl(getUrl())
    }
}
