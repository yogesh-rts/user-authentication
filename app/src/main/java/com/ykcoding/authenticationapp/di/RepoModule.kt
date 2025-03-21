package com.ykcoding.authenticationapp.di

import com.ykcoding.authenticationapp.network.service.SessionRepo
import com.ykcoding.authenticationapp.network.service.SessionRepoImpl
import org.koin.dsl.module

val repoModule = module {
    single<SessionRepo> { SessionRepoImpl(service = get()) }
}