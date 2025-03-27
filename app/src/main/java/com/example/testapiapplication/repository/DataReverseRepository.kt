package com.example.testapiapplication.repository

import com.example.testapiapplication.di.BaseUrl2
import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.data.response.DataReverseResponse
import retrofit2.Response
import javax.inject.Inject

class DataReverseRepository @Inject constructor(
    @BaseUrl2 private val retrofitAPI: RetrofitAPI
){
    suspend fun getDataReverse(lat: Double, lon: Double): Response<List<DataReverseResponse>> {
        return retrofitAPI.getDataReverse(lat, lon)
    }
}