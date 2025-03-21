package com.ykcoding.authenticationapp.helper

import android.content.Context
import android.content.SharedPreferences
import com.ykcoding.authenticationapp.network.model.session.SessionModel
import com.ykcoding.authenticationapp.network.model.session.SessionUpdatedModel

object SessionManager {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    object Config {
        const val PRIVATE_MODE = Context.MODE_PRIVATE
        const val PREFERENCES_NAME = "AuthenticationPrefs"
    }

    fun init(context: Context) {
        sharedPreferences = context.applicationContext.getSharedPreferences(Config.PREFERENCES_NAME, Config.PRIVATE_MODE)
        editor = sharedPreferences.edit()
    }

    object Key {

        object Session {
            const val TOKEN = "session_token"
            const val REFRESH_TOKEN = "session_refresh_token"
            //const val USERNAME = "username"
            //const val PASSWORD = "password"
        }
    }

    fun saveSession(session: SessionModel) {
        editor.apply {
            putString(Key.Session.TOKEN, session.accessToken)
            putString(Key.Session.REFRESH_TOKEN, session.refreshToken)
            apply()
        }
    }

    fun saveSession(session: SessionUpdatedModel) {
        editor.apply {
            putString(Key.Session.TOKEN, session.accessToken)
            putString(Key.Session.REFRESH_TOKEN, session.refreshToken)
            apply()
        }
    }

    fun getSessionToken() = getString(Key.Session.TOKEN, "")

    fun getSessionRefreshToken() = getString(Key.Session.REFRESH_TOKEN, "")


}