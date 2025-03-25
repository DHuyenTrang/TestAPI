package com.example.testapiapplication.repository

import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.request.UserRequest
import com.example.testapiapplication.response.UserResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {
    suspend fun login(phoneNumber: String, password: String): Response<UserResponse> {
        return retrofitAPI.login(UserRequest(phoneNumber, password))
    }
}