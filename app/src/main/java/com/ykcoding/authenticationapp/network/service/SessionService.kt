package com.ykcoding.authenticationapp.network.service

import com.ykcoding.authenticationapp.network.Endpoint
import com.ykcoding.authenticationapp.network.Param
import com.ykcoding.authenticationapp.network.model.session.SessionModel
import com.ykcoding.authenticationapp.network.model.session.SessionUpdatedModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SessionService {

    @FormUrlEncoded
    @POST(Endpoint.AUTHENTICATION + "/" + Endpoint.LOGIN)
    suspend fun createSession(
        @Field(Param.Field.USERNAME) username: String,
        @Field(Param.Field.PASSWORD) password: String
    ): SessionModel

    @FormUrlEncoded
    @POST(Endpoint.AUTHENTICATION + "/" + Endpoint.REFRESH)
    suspend fun updateSession(
        @Field(Param.Field.REFRESH_TOKEN) refreshToken: String
    ): SessionUpdatedModel

}