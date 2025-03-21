package com.ykcoding.authenticationapp.network.service

import com.ykcoding.authenticationapp.network.NetworkResponse
import com.ykcoding.authenticationapp.network.model.session.SessionModel
import com.ykcoding.authenticationapp.network.model.session.SessionUpdatedModel

interface SessionRepo {

    suspend fun create(username: String, password: String): NetworkResponse<SessionModel>

    suspend fun update(refreshToken: String): NetworkResponse<SessionUpdatedModel>
}

//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5Lmpva
// G5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpb
// WFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3NDE4OTg4ODQsImV4cCI6MTc0NDQ5MDg4NH0.cPn1paojR-PWCO6fVzl7wcc7qMjO0I4NZt14B1UbSQM