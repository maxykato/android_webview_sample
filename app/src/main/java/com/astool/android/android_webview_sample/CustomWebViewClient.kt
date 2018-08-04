package com.astool.android.android_webview_sample

import android.app.Activity
import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient

interface CustomWebViewClientDelegate {
    fun onPageFinished(view: WebView?, benchmark: Benchmark)
}

class CustomWebViewClient(private val activity: Activity, private val delegate: CustomWebViewClientDelegate): WebViewClient() {
    var benchmarks: MutableList<Benchmark> = mutableListOf()

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        val benchmark = Benchmark(url ?: "", System.currentTimeMillis())
        benchmarks.add(benchmark)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        val benchmark = benchmarks.firstOrNull { it.url == url && it.finishTime == null }
        benchmark?.run {
            finishTime = System.currentTimeMillis()
            delegate.onPageFinished(view, this)
        }
    }
}