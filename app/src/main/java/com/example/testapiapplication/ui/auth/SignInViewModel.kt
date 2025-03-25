package com.example.testapiapplication.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapiapplication.TokenManager
import com.example.testapiapplication.UserPrefs
import com.example.testapiapplication.repository.AuthRepository
import com.example.testapiapplication.request.UserRequest
import com.example.testapiapplication.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenManager: TokenManager,
    private val userPrefs: UserPrefs
) : ViewModel() {

    private val _isLoginSuccessful = MutableStateFlow<Boolean?>(null)
    val isLoginSuccessful: StateFlow<Boolean?> = _isLoginSuccessful.asStateFlow()

    fun fetchUser(phoneNumber: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.login(phoneNumber, password)

                if (response.isSuccessful) {
                    response.body()?.let {
                        tokenManager.saveToken(it.access_token, it.refresh_token)
                        userPrefs.saveUserId(it.customer_id)
                        _isLoginSuccessful.value = true
                        Log.d("Auth", "Login Success: ${it.access_token}")
                    }
                } else {
                    Log.e("Auth", "Login Failed: ${response.errorBody()?.string()}")
                    _isLoginSuccessful.value = false
                }
            } catch (e: Exception) {
                Log.e("Auth", "API Call Error: ${e.localizedMessage}")
                _isLoginSuccessful.value = false
            }
        }
    }
}


// "phone_number": "0385852404",
//    "password": "Lumivn@274"