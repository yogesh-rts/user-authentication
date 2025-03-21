package com.ykcoding.authenticationapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykcoding.authenticationapp.helper.LiveDataEvent
import com.ykcoding.authenticationapp.network.NetworkResponse
import com.ykcoding.authenticationapp.network.service.SessionRepo
import com.ykcoding.authenticationapp.network.service.SessionRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val sessionRepo: SessionRepo) : ViewModel()  {

    private val _errorResponse: MutableStateFlow<LiveDataEvent<NetworkResponse.Error>?> = MutableStateFlow(null)
    val errorResponse = _errorResponse.asStateFlow()


    fun login(userName: String, password: String, onSuccess: () -> Unit) {

        viewModelScope.launch {

            when(val response = sessionRepo.create(userName, password)) {
                is NetworkResponse.Success -> {
                    Log.d("LoginViewModel", "Login successful")
                    onSuccess.invoke()
                }
                is NetworkResponse.Error -> {
                    Log.d("LoginViewModel", "Login Failed")
                    _errorResponse.value = LiveDataEvent(response)
                }
            }
        }

    }

}