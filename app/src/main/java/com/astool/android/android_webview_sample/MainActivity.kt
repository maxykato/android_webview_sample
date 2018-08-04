package com.astool.android.android_webview_sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.webkit.WebView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.MalformedURLException
import java.net.URL


class MainActivity : AppCompatActivity(), CustomWebViewClientDelegate {
    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {
            // Do nothing.
            return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            val url = try {
                URL(query).toString()
            } catch (e: MalformedURLException) {
                Toast.makeText(applicationContext, "URL is not good ^^", Toast.LENGTH_LONG).show()
                ""
            }
            webView.loadUrl(url)
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.run {
            webViewClient = CustomWebViewClient(this@MainActivity)
            webChromeClient = CustomWebChromeClient(this@MainActivity)
            settings.setSupportMultipleWindows(true)
        }
//        webView.loadUrl("https://www.google.com")
        webView.loadUrl("http://game.granbluefantasy.jp/")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val searchView = menu?.findItem(R.id.searchView)?.actionView as SearchView
        searchView.setOnQueryTextListener(onQueryTextListener)

        return true
    }

    override fun onPageFinished(view: WebView?, benchmark: Benchmark) {
        val a = benchmark.loadTime
        benchmarkView.text = this.resources.getString(R.string.benchmark_text, benchmark.url, benchmark.loadTime)
    }
}
