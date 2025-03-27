package com.ykcoding.authenticationapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykcoding.authenticationapp.helper.LiveDataEvent
import com.ykcoding.authenticationapp.network.NetworkResponse
import com.ykcoding.authenticationapp.network.service.SessionRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val sessionRepo: SessionRepo) : ViewModel()  {

    private val _usernameTextState = MutableStateFlow("")
    val usernameTextState = _usernameTextState.asStateFlow()

    private val _passwordTextState = MutableStateFlow("")
    val passwordTextState = _passwordTextState.asStateFlow()

    private val _usernameErrorState = MutableStateFlow(ErrorType.NONE)
    val usernameErrorType = _usernameErrorState.asStateFlow()

    private val _passwordErrorState = MutableStateFlow(ErrorType.NONE)
    val passwordErrorState = _passwordErrorState.asStateFlow()

    private val _response: MutableStateFlow<LiveDataEvent<NetworkResponse<*>>?> = MutableStateFlow(null)
    val response = _response.asStateFlow()


    fun setUsername(username: String) {
        _usernameTextState.value = username
        isUsernameValid()
    }

    fun setPassword(password: String) {
        _passwordTextState.value = password
        isPasswordValid()
    }

    private fun isUsernameValid() {
        _usernameErrorState.value = if (_usernameTextState.value.isEmpty()) ErrorType.EMPTY else ErrorType.NONE
    }

    private fun isPasswordValid() {
        _passwordErrorState.value = if (_passwordTextState.value.isEmpty()) ErrorType.EMPTY else ErrorType.NONE
    }

    fun login(userName: String, password: String) {

        viewModelScope.launch {

            when(val response = sessionRepo.create(userName, password)) {
                is NetworkResponse.Success -> {
                    Log.d("LoginViewModel", "Login successful")
                    _response.value = LiveDataEvent(response)
                }
                is NetworkResponse.Error -> {
                    Log.d("LoginViewModel", "Login Failed")
                    _response.value = LiveDataEvent(response)
                }
            }
        }

    }

    enum class ErrorType {
        EMPTY,
        NONE
    }

}