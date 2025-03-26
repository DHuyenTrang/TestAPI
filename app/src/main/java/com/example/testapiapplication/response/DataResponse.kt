package com.example.testapiapplication.response

data class DataResponse(
    val osm_id: Long,
    val datapoint: List<DataPoint>,
    val relation: List<String>,
)

data class DataPoint(
    val compass: String,
    val lat: Double,
    val lon: Double,
    val osm_id: String,
    val direction: Double,
    val traffic_sign_id: Int,
    val id: Int,
)
