package com.example.testapiapplication.repository

import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.response.ProfileResponse
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val retrofitAPI: RetrofitAPI){
    suspend fun getProfile(accessToken: String, userId: String): Response<ProfileResponse>
    = retrofitAPI.getProfile(accessToken, userId)
}