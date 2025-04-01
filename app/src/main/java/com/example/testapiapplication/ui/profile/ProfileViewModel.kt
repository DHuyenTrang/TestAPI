package com.example.testapiapplication.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testapiapplication.TokenManager
import com.example.testapiapplication.repository.ProfileRepository
import com.example.testapiapplication.data.response.ProfileResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _profileResponse = MutableStateFlow<ProfileResponse?>(null)
    val profileResponse: StateFlow<ProfileResponse?> = _profileResponse

    suspend fun fetchProfile() {
        Log.d("Fetch Data", "Start fetch Profile ${System.currentTimeMillis()}")

        //viewModelScope.launch {
            try {
                val response = profileRepository.getProfile()
                if (response.isSuccessful) {
                    _profileResponse.value = response.body()
                    Log.d("ProfileViewModel", "Profile fetched successfully: ${response.body()}")
                } else {
                    Log.e("ProfileViewModel", "Failed to fetch profile: ${response.errorBody()?.string()}")

                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "API Error: ${e.localizedMessage}")
            }
        //}
        Log.d("Fetch Data", "Complete fetch Profile ${System.currentTimeMillis()}")
    }
    fun logout() {
        tokenManager.clearToken()
    }

}