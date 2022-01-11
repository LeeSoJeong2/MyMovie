package com.example.mymovie

import com.android.volley.RequestQueue

class AppHelper {
    companion object {
        lateinit var requestQueue: RequestQueue
        val host = "boostcourse-appapi.connect.or.kr"
        val port = 10000
    }
}