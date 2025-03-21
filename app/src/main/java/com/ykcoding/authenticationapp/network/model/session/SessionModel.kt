package com.ykcoding.authenticationapp.network.model.session

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SessionModel(
    @Json(name = "accessToken")
    val accessToken: String,

    @Json(name = "refreshToken")
    val refreshToken: String,

    @Json(name = "id")
    val id: String,

    @Json(name = "username")
    val username: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "firstName")
    val firstName: String,

    @Json(name = "lastName")
    val lastName: String,

    @Json(name = "gender")
    val gender: String,

    @Json(name = "image")
    val imageUrl: String,

)
