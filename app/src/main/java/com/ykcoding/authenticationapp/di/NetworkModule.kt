package com.ykcoding.authenticationapp.di

import com.squareup.moshi.Moshi
import com.ykcoding.authenticationapp.network.AuthorizationHeaderInterceptor
import com.ykcoding.authenticationapp.network.ServerManager
import com.ykcoding.authenticationapp.network.service.SessionRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


private const val TIME_OUT = 15

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single { Moshi.Builder().build() }
    single { AuthorizationHeaderInterceptor(lazy { get<SessionRepo>() }) }
    single(named(enum = QualifiedName.AUTH_INTERCEPTOR)) {
        println("Retrofit : With Auth")
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<AuthorizationHeaderInterceptor>())
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    single(named(enum = QualifiedName.NO_AUTH_INTERCEPTOR)) {
        println("Retrofit : Without Auth")
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    single(named(enum = QualifiedName.AUTH_INTERCEPTOR)) {
        provideRetrofit(
            get(named(enum =  QualifiedName.AUTH_INTERCEPTOR)),
            get()
        )
    }
    single(named(enum = QualifiedName.NO_AUTH_INTERCEPTOR)) {
        provideRetrofit(
            get(named(enum = QualifiedName.NO_AUTH_INTERCEPTOR)),
            get()
        )
    }
}

private fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(ServerManager.url)
        .build()
}



