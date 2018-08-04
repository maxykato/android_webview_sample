package com.astool.android.android_webview_sample

import android.webkit.WebView

fun WebView.setup() {
    val INITIALIZE_URL = "https://s3-ap-northeast-1.amazonaws.com/test-morimori/grablue-webpage-test/index.html"

    settings.javaScriptCanOpenWindowsAutomatically = true
    settings.javaScriptEnabled = true
    settings.setSupportMultipleWindows(true)
    loadUrl(INITIALIZE_URL)
}