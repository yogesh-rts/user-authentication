package com.ykcoding.authenticationapp.helper

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.ykcoding.authenticationapp.network.model.session.SessionModel
import com.ykcoding.authenticationapp.network.model.session.SessionUpdatedModel

class SessionHandler(context: Context) {

    var sharedPreferences: SharedPreferences
    var editor: Editor

    object Config {
        const val PRIVATE_MODE = Context.MODE_PRIVATE
        const val PREFERENCES_NAME = "AuthenticationPrefs"
    }

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(Config.PREFERENCES_NAME, Config.PRIVATE_MODE)
        editor = sharedPreferences.edit()
    }

    object Key {
        object Session {
            const val ACCESS_TOKEN = "access_token"
            const val REFRESH_TOKEN = "refresh_token"
        }
    }

    fun saveSession(session: SessionModel) {
        putString(Key.Session.ACCESS_TOKEN, session.accessToken)
        putString(Key.Session.REFRESH_TOKEN, session.refreshToken)
        editor.apply()
    }

    fun saveSession(session: SessionUpdatedModel) {
        putString(Key.Session.ACCESS_TOKEN, session.accessToken)
        putString(Key.Session.REFRESH_TOKEN, session.refreshToken)
        editor.apply()
    }

    fun getSessionAccessToken() = getString(Key.Session.ACCESS_TOKEN, "")

    fun getSessionRefreshToken() = getString(Key.Session.REFRESH_TOKEN, "")
}