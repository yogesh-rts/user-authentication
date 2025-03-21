package com.ykcoding.authenticationapp.network

import com.ykcoding.authenticationapp.helper.SessionHandler
import com.ykcoding.authenticationapp.helper.isLoggedIn
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

    //private val sessionManager by lazy { SessionManager }
    private val sessionHandler: SessionHandler by inject<SessionHandler>()

    override fun intercept(chain: Interceptor.Chain): Response {
        return when(sessionHandler.isLoggedIn()) {
            true -> {
                var response = chain.proceed(buildAuthorizedRequest(chain.request(), sessionHandler.getSessionAccessToken()))

                if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    response.close()
                    sendRefreshToken(sessionHandler.getSessionRefreshToken())

                    response = chain.proceed(buildAuthorizedRequest(chain.request(), sessionHandler.getSessionAccessToken()))
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