package com.example.testapiapplication.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapiapplication.TokenManager
import com.example.testapiapplication.UserPrefs
import com.example.testapiapplication.repository.AuthRepository
import com.example.testapiapplication.repository.ProfileRepository
import com.example.testapiapplication.response.ProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _profileResponse = MutableStateFlow<ProfileResponse?>(null)
    val profileResponse: StateFlow<ProfileResponse?> = _profileResponse

    fun fetchProfile() {

        viewModelScope.launch {
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
        }
    }
    fun logout() {
        tokenManager.clearToken()
    }

}