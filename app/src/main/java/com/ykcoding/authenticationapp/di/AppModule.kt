package com.ykcoding.authenticationapp.di

import com.ykcoding.authenticationapp.helper.SessionManager
import org.koin.dsl.module

val appModule = module {
    single { SessionManager(get()) }
}