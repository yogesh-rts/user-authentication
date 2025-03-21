package com.ykcoding.authenticationapp.helper

open class LiveDataEvent<out T> (private val content: T) {

    private var hasBeenHandled = false

    fun getIfContentHasBeenHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}