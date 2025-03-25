package com.example.testapiapplication.network

import com.example.testapiapplication.request.UserRequest
import com.example.testapiapplication.response.ProfileResponse
import com.example.testapiapplication.response.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitAPI {
    @POST("/v5/auth/login-with-password")
    suspend fun login(
        @Body userRequest: UserRequest
    ): Response<UserResponse>

    @GET("/v4/api/user/profile-app")
    suspend fun getProfile(
        @Header("Authorization") accessToken: String,
        @Header("x-id") userId: String,
    ): Response<ProfileResponse>
}