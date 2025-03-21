package com.ykcoding.authenticationapp.helper.ext

import com.ykcoding.authenticationapp.helper.SessionManager


fun SessionManager.getNullableString(key: String, defValue: String? = null): String? {
    return this.sharedPreferences.getString(key, defValue)
}

fun SessionManager.getString(key: String, defValue: String): String {
    return this.sharedPreferences.getString(key, defValue) ?: defValue
}

fun SessionManager.isLoggedIn(): Boolean = getNullableString(SessionManager.Key.Session.ACCESS_TOKEN) != null

fun SessionManager.putString(key: String, defValue: String) {
    editor.putString(key, defValue)
}

