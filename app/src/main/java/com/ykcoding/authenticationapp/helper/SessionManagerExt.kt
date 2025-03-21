package com.ykcoding.authenticationapp.helper


fun SessionManager.getNullableString(key: String ,defValue: String? = null): String? {
    return this.sharedPreferences.getString(key, defValue)
}

fun SessionManager.getString(key: String, defValue: String): String {
    return this.sharedPreferences.getString(key, defValue) ?: defValue
}

fun SessionManager.isLoggedIn(): Boolean = getNullableString(SessionManager.Key.Session.TOKEN) != null

fun SessionHandler.putString(key: String, defValue: String) {
    editor.putString(key, defValue)
}

fun SessionHandler.getString(key: String, defValue: String): String {
    return this.sharedPreferences.getString(key, defValue) ?: defValue
}

fun SessionHandler.getNullableString(key: String ,defValue: String? = null): String? {
    return this.sharedPreferences.getString(key, defValue)
}

fun SessionHandler.isLoggedIn(): Boolean = getNullableString(SessionHandler.Key.Session.ACCESS_TOKEN) != null