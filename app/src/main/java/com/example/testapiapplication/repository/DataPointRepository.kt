package com.example.testapiapplication.repository

import com.example.testapiapplication.network.RetrofitAPI
import javax.inject.Inject

class DataPointRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {
    suspend fun fetchDataPoints(lat: Double, lon: Double) = retrofitAPI.getDataPoint(lat, lon)
}