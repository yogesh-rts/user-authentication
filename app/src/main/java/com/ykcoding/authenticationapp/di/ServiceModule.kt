package com.ykcoding.authenticationapp.di

import com.ykcoding.authenticationapp.network.service.SessionService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single { provideRetrofitService<SessionService>(retrofit = get(named(enum =  QualifiedName.AUTH_INTERCEPTOR))) }
}

private inline fun <reified T> provideRetrofitService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}