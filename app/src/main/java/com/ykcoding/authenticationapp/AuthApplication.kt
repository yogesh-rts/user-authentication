package com.ykcoding.authenticationapp

import android.app.Application
import android.content.Context
import com.ykcoding.authenticationapp.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AuthApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin(this@AuthApplication)
    }

    private fun setupKoin(androidContext: Context) {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(androidContext)
            modules(allModules)

        }
    }
}