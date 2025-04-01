package com.example.testapiapplication.repository

import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.data.response.ProfileResponse
import retrofit2.Response

class ProfileRepository(
    private val retrofitAPI: RetrofitAPI
){
    suspend fun getProfile(): Response<ProfileResponse>
    = retrofitAPI.getProfile()
}