package com.astool.android.android_webview_sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBindings()
    }

    private fun setupBindings() {
        webViewTestButton.setOnClickListener {
            present(WebViewActivity::class.java)
        }

        googleSignInTestButton.setOnClickListener {
            present(GoogleSignInActivity::class.java)
        }
    }

    private fun <T: Activity> present(clazz: Class<T>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}
