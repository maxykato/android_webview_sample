package com.astool.android.android_webview_sample

import android.app.Activity
import android.os.Message
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_webview.*

class CustomWebChromeClient(private val activity: Activity): WebChromeClient() {
    override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
        val newWindowWebView = WebView(activity)
        newWindowWebView.run {
            setup()
            val delegate = activity as CustomWebViewClientDelegate
            webViewClient = CustomWebViewClient(activity, delegate)
            webChromeClient = CustomWebChromeClient(activity)
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        (activity.webView.parent as ViewGroup).addView(newWindowWebView)

        val transport = resultMsg?.obj as WebView.WebViewTransport
        transport.webView = newWindowWebView
        resultMsg.sendToTarget()
        return true
    }

    override fun onCloseWindow(window: WebView?) {
        super.onCloseWindow(window)

        (activity.webView.parent as ViewGroup).removeView(window)
    }
}
