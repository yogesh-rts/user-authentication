package com.ykcoding.authenticationapp.di

import com.ykcoding.authenticationapp.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(sessionRepo = get()) }
}