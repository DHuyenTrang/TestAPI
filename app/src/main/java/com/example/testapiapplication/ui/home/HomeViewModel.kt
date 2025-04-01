package com.example.testapiapplication.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testapiapplication.repository.DataPointRepository
import com.example.testapiapplication.data.response.DataResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val dataPointRepository: DataPointRepository,
) : ViewModel() {
    private val _dataResponses = MutableStateFlow<List<DataResponse>?>(null)
    val dataResponses: StateFlow<List<DataResponse>?> = _dataResponses.asStateFlow()

    suspend fun fetchData(lat: Double, lon: Double) {
        Log.d("Fetch Data", "Start fetch Data Point ${System.currentTimeMillis()}")
//        viewModelScope.launch(Dispatchers.IO) {
            val response =
                dataPointRepository.fetchDataPoints(lat, lon)

            if (response.isSuccessful) {
                response.body()?.let {
                    _dataResponses.value = it
                }
            } else {
                Log.e("HomeViewModel", "Failed to fetch data: ${response.errorBody()?.string()}")
            }
//        }
        Log.d("Fetch Data", "Complete fetch Data Point ${System.currentTimeMillis()}")

    }
}