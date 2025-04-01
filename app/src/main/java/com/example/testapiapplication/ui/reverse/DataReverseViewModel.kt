package com.example.testapiapplication.ui.reverse

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapiapplication.data.response.DataReverseResponse
import com.example.testapiapplication.model.DataReverse
import com.example.testapiapplication.repository.DataReverseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataReverseViewModel (
    private val dataReverseRepository: DataReverseRepository,
): ViewModel() {
    private var _dataReverse = MutableStateFlow<List<DataReverse>?>(emptyList())
    val dataReverse: StateFlow<List<DataReverse>?> = _dataReverse.asStateFlow()

    fun fetchData(lat: Double, lon: Double) {
        viewModelScope.launch {
            val response = dataReverseRepository.getDataReverse(lat, lon)
            if (response.isSuccessful) {
                val listData: MutableList<DataReverse> = mutableListOf()
                Log.d("ReverseViewModel", "Success to fetch data: ${response.body()}")
                response.body()?.let {
                    for(item: DataReverseResponse in it) {
                        listData.add(item.mapToDataReverse())
                    }
                    _dataReverse.value = listData
                }
            }
            else {
                Log.e("ReverseViewModel", "Failed to fetch data: ${response.errorBody()?.string()}")
            }
        }
    }
}