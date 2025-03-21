package com.ykcoding.authenticationapp.network.service

import com.ykcoding.authenticationapp.helper.SessionHandler
import com.ykcoding.authenticationapp.helper.SessionManager
import com.ykcoding.authenticationapp.network.NetworkResponse
import com.ykcoding.authenticationapp.network.model.session.SessionModel
import com.ykcoding.authenticationapp.network.model.session.SessionUpdatedModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import java.io.IOException

class SessionRepoImpl(
    private val service: SessionService
): SessionRepo, KoinComponent {

    //private val sessionManager by lazy { SessionManager }
    private val sessionHandler: SessionHandler by inject<SessionHandler>()

    override suspend fun create(username: String, password: String): NetworkResponse<SessionModel> {

        return try {
            val response = service.createSession(username = username, password = password)
            sessionHandler.saveSession(response)
            NetworkResponse.Success(response)
        } catch (e: HttpException) {
            NetworkResponse.Error.Api(code = e.code(), message = e.message())
        } catch (e: IOException) {
            NetworkResponse.Error.Network(error = e)
        } catch (e: Throwable) {
            NetworkResponse.Error.Unknown(error = e)
        }

    }

    override suspend fun update(refreshToken: String): NetworkResponse<SessionUpdatedModel> {

        return try {
            val response = service.updateSession(refreshToken = refreshToken)
            sessionHandler.saveSession(response)
            NetworkResponse.Success(response)

        } catch (e: HttpException) {
            NetworkResponse.Error.Api(code = e.code(), message = e.message())
        } catch (e: IOException) {
            NetworkResponse.Error.Network(error = e)
        } catch (e: Throwable) {
            NetworkResponse.Error.Unknown(error = e)
        }

    }

}