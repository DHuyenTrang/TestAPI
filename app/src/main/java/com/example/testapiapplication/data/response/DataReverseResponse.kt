package com.example.testapiapplication.data.response

import com.example.testapiapplication.model.DataReverse

data class DataReverseResponse(
    val place_id: Int,
    val license: String,
//    val osm_type: String,
    val osm_id: Long,
    val lat: String,
    val lon: String,
    val category: String,
    val type: String,
    val place_rank: Int,
    val importance: Double,
    val address_type: String,
    val name: String,
    val display_name: String,
    val address: Address,
    val extratags: Map<String, String> = emptyMap(),
    val boundingbox: List<String>,
    val geometry: Geometry,
) {
    fun mapToDataReverse(): DataReverse {
        return DataReverse(
            place_id = place_id,
            displayName = display_name,
            turn_lanes = extratags["turn:lanes"] ?: "",
        )
    }
}

data class Address(
    val road: String,
    val quarter: String,
    val suburb: String,
    val city: String,
    val ISO3166_2_lvl4: String,
    val postcode: String,
    val country: String,
    val country_code: String,
) {

}

data class Geometry(
    val type: String,
    val coordinates: List<List<Double>>,
) {

}
