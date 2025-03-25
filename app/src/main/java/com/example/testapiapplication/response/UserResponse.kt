package com.example.testapiapplication.response

data class UserResponse(
    val customer_id: String,
    val access_token: String,
    val refresh_token: String,
) {

}