package com.example.testapiapplication.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapiapplication.TokenManager
import com.example.testapiapplication.UserPrefs
import com.example.testapiapplication.repository.DataPointRepository
import com.example.testapiapplication.response.DataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataPointRepository: DataPointRepository,
    private val tokenManager: TokenManager,
) : ViewModel() {
    private val _dataResponses = MutableStateFlow<List<DataResponse>?>(null)
    val dataResponses: StateFlow<List<DataResponse>?> = _dataResponses.asStateFlow()

    fun fetchData(lat: Double, lon: Double) {
        val userId = tokenManager.getUserId() ?: ""
//        Log.d("Authorization", "accessToken: $accessToken")
        viewModelScope.launch {
            val response =
                dataPointRepository.fetchDataPoints(lat, lon)

            if (response.isSuccessful) {
                response.body()?.let {
                    _dataResponses.value = it
                }
            } else {
                Log.e("HomeViewModel", "Failed to fetch data: ${response.errorBody()?.string()}")
            }
        }
    }
}