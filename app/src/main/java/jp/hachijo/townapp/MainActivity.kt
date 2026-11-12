package jp.hachijo.townapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowInsets
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themeColor = Color.rgb(7, 89, 133)
        window.statusBarColor = themeColor
        window.navigationBarColor = themeColor

        webView = WebView(this)
        webView.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = false
        webView.webChromeClient = WebChromeClient()

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val uri = request?.url ?: return false

                if (uri.scheme == "mailto") {
                    return try {
                        val intent = Intent(Intent.ACTION_SENDTO, uri)
                        startActivity(intent)
                        true
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            this@MainActivity,
                            "メールアプリが見つかりません",
                            Toast.LENGTH_LONG
                        ).show()
                        true
                    }
                }

                // X（旧Twitter）のリンクは、Xアプリで直接開く（なければブラウザで開く）
                val host = uri.host ?: ""
                val isX = host == "x.com" || host.endsWith(".x.com") ||
                    host == "twitter.com" || host.endsWith(".twitter.com")
                if (isX) {
                    openInXApp(uri)
                    return true
                }

                return if (
                    uri.scheme == "https" ||
                    uri.scheme == "http" ||
                    uri.scheme == "file"
                ) {
                    false
                } else {
                    true
                }
            }
        }

        // 画面の上下（ステータスバー・ナビゲーションバー）に、アプリの画面が重ならないようにする
        val root = FrameLayout(this)
        root.setBackgroundColor(themeColor)
        root.addView(webView)
        root.setOnApplyWindowInsetsListener { v, insets ->
            if (Build.VERSION.SDK_INT >= 30) {
                val bars = insets.getInsets(
                    WindowInsets.Type.systemBars() or WindowInsets.Type.displayCutout()
                )
                v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
                WindowInsets.CONSUMED
            } else {
                v.setPadding(
                    insets.systemWindowInsetLeft,
                    insets.systemWindowInsetTop,
                    insets.systemWindowInsetRight,
                    insets.systemWindowInsetBottom
                )
                insets.consumeSystemWindowInsets()
            }
        }
        setContentView(root)

        if (savedInstanceState == null) {
            webView.loadUrl("file:///android_asset/index.html")
        } else {
            webView.restoreState(savedInstanceState)
        }
    }

    private fun openInXApp(uri: Uri) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.twitter.android")
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            } catch (e2: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "Xを開けるアプリが見つかりません",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    @Deprecated("Deprecated in Android API")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
