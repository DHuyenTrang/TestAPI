package com.example.testapiapplication.network

import com.example.testapiapplication.request.RefreshTokenRequest
import com.example.testapiapplication.request.UserRequest
import com.example.testapiapplication.response.DataResponse
import com.example.testapiapplication.response.ProfileResponse
import com.example.testapiapplication.response.RefreshTokenResponse
import com.example.testapiapplication.response.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitAPI {
    @POST("/v5/auth/login-with-password")
    suspend fun login(
        @Body userRequest: UserRequest
    ): Response<UserResponse>

    @POST("/v5/auth/refresh-token-new")
    fun getRefreshToken(
        @Body refreshToken: RefreshTokenRequest,
    ): Call<RefreshTokenResponse>

    @GET("/v4/api/user/profile-app")
    suspend fun getProfile(): Response<ProfileResponse>

    @GET("/v5/datapoint/search-datapoint")
    suspend fun getDataPoint(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Response<List<DataResponse>>
}