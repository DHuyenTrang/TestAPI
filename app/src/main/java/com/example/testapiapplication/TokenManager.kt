package com.example.testapiapplication

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class TokenManager @Inject constructor(context: Context) {
    val sharePrefs: SharedPreferences = context.getSharedPreferences("authPrefs", Context.MODE_PRIVATE)

    fun saveToken(accessToken: String, refreshToken: String) {
        sharePrefs.edit().apply {
            putString("accessToken", accessToken)
            putString("refreshToken", refreshToken)
            apply()
            Log.d("TokenManager", "saveToken: $accessToken, $refreshToken")
        }
    }

    fun getAccessToken(): String? {
        return sharePrefs.getString("accessToken", null)
    }

    fun getRefreshToken(): String? {
        return sharePrefs.getString("refreshToken", null)
    }
}