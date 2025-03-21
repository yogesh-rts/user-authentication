package com.ykcoding.authenticationapp.network

import java.io.IOException

sealed class NetworkResponse<out T: Any> {

    data class Success<T: Any>(val body: T) : NetworkResponse<T>()

    sealed class Error : NetworkResponse<Nothing>() {
        data class Api(val code: Int, val message: String): Error()
        data class Network(val error: IOException): Error()
        data class Unknown(val error: Throwable): Error()

        fun handleErrorResponse(): String {
            return when(this) {
                is Api -> "HTTP Error: code: $code - message: $message"
                is Network -> "Please check your network connection"
                is Unknown -> "Something went wrong"
            }
        }
    }
}