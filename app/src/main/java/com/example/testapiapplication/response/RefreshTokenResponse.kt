package com.example.testapiapplication.response

data class RefreshTokenResponse(
    val access_token: String,
    val refresh_token: String
) {
}