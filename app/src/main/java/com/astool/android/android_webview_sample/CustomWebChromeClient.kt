package com.astool.android.android_webview_sample

import android.app.Activity
import android.graphics.Bitmap
import android.os.Message
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebChromeClient(val activity: Activity): WebChromeClient() {
    override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
        val newWindowWebView = WebView(activity)
        newWindowWebView.webViewClient = WebViewClient()

        val transport = resultMsg?.obj as WebView.WebViewTransport
        transport.webView = newWindowWebView
        resultMsg.sendToTarget()
        return true
    }
}
