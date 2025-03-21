package com.ykcoding.authenticationapp.network

import com.ykcoding.authenticationapp.helper.SessionManager
import com.ykcoding.authenticationapp.helper.ext.isLoggedIn
import com.ykcoding.authenticationapp.network.service.SessionRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.net.HttpURLConnection

class AuthorizationHeaderInterceptor(private val sessionRepo: Lazy<SessionRepo>): Interceptor, KoinComponent {

    private val sessionManager: SessionManager by inject<SessionManager>()

    override fun intercept(chain: Interceptor.Chain): Response {
        return when(sessionManager.isLoggedIn()) {
            true -> {
                var response = chain.proceed(buildAuthorizedRequest(chain.request(), sessionManager.getSessionAccessToken()))

                if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    response.close()
                    sendRefreshToken(sessionManager.getSessionRefreshToken())

                    response = chain.proceed(buildAuthorizedRequest(chain.request(), sessionManager.getSessionAccessToken()))
                }


                response
            }
            false -> {
                chain.proceed(buildAuthorizedRequest(chain.request()))
            }
        }
    }

    private fun buildAuthorizedRequest(unauthorizedRequest: Request, token: String? = null) : Request {
        return unauthorizedRequest.newBuilder()
            .header("Accept", "application/json").apply {
                token?.let {
                    header("Authorization", it)
                }
            }
            .build()
    }

    private fun sendRefreshToken(refreshToken: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = sessionRepo.value.update(refreshToken)

            if (response !is NetworkResponse.Success) {
                // Logout
            }
        }

    }

}