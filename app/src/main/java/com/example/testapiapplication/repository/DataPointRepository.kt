package com.example.testapiapplication.repository

import com.example.testapiapplication.network.RetrofitAPI

class DataPointRepository (
    private val retrofitAPI: RetrofitAPI
) {
    suspend fun fetchDataPoints(lat: Double, lon: Double) = retrofitAPI.getDataPoint(lat, lon)
}