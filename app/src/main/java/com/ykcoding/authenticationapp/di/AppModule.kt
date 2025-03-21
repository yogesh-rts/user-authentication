package com.ykcoding.authenticationapp.di

import com.ykcoding.authenticationapp.helper.SessionHandler
import org.koin.dsl.module

val appModule = module {
    single { SessionHandler(get()) }
}