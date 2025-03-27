package com.example.testapiapplication.data.response

data class RefreshTokenResponse(
    val access_token: String,
    val refresh_token: String
) {
}