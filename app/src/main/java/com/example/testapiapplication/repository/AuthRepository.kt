package com.example.testapiapplication.repository

import com.example.testapiapplication.di.BaseUrl1
import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.data.request.UserRequest
import com.example.testapiapplication.data.response.UserResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    @BaseUrl1 private val retrofitAPI: RetrofitAPI
) {
    suspend fun login(phoneNumber: String, password: String): Response<UserResponse> {
        return retrofitAPI.login(UserRequest(phoneNumber, password))
    }
}