package com.ykcoding.authenticationapp.network

object ServerManager {

    private const val HTTP = "http://"
    private const val HTTP_SECURE = "https://"

    object UseSsl {
        const val DEFAULT = true
    }

    private var baseURL = "dummyjson.com"
    private var useSSL = UseSsl.DEFAULT

    val url: String
        get() = when(useSSL) {
            true -> HTTP_SECURE + baseURL
            false -> HTTP + baseURL
        }

}