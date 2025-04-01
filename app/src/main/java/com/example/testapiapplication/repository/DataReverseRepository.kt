package com.example.testapiapplication.repository

import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.data.response.DataReverseResponse
import retrofit2.Response

class DataReverseRepository(
    private val retrofitAPI: RetrofitAPI
){
    suspend fun getDataReverse(lat: Double, lon: Double): Response<List<DataReverseResponse>> {
        return retrofitAPI.getDataReverse(lat, lon)
    }
}