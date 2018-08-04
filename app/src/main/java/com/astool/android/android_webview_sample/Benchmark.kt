package com.astool.android.android_webview_sample

import java.util.*

data class Benchmark(val url: String, val startTime: Long, var finishTime: Long? = null) {
    var loadTime: Long? = null // milliseconds
        get() = finishTime?.let {
            it - startTime
        }
}