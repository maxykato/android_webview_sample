package com.astool.android.android_webview_sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup
import android.support.v7.widget.ActionBarOverlayLayout
import android.support.v7.widget.Toolbar
import android.text.Layout
import com.astool.android.android_webview_sample.R.layout.activity_main





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewGroup = findViewById<Toolbar>(R.id.tool_bar).parent as? ViewGroup
        while (viewGroup != null) {
            if (viewGroup is ActionBarOverlayLayout) {
                viewGroup = viewGroup.parent as ViewGroup
                viewGroup.addView(search_view)
                break
            }
            viewGroup = viewGroup.parent as ViewGroup
        }

        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //Do some magic
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //Do some magic
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val item = menu?.findItem(R.id.action_search)
        search_view.setMenuItem(item)

        return true
    }
}
