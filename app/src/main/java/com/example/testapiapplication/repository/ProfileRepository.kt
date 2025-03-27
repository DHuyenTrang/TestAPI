package com.example.testapiapplication.repository

import com.example.testapiapplication.di.BaseUrl1
import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.data.response.ProfileResponse
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    @BaseUrl1 private val retrofitAPI: RetrofitAPI
){
    suspend fun getProfile(): Response<ProfileResponse>
    = retrofitAPI.getProfile()
}