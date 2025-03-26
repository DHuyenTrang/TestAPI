package com.example.testapiapplication

import android.content.Context
import javax.inject.Inject

class UserPrefs @Inject constructor(context: Context) {
    val sharePrefs = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)

    fun saveUserId(userId: String) {
        sharePrefs.edit().apply {
            putString("user_id", userId)
            apply()
        }
    }

    fun getUserId(): String? {
        return sharePrefs.getString("user_id", null)
    }

    fun clearUserId() {
        sharePrefs.edit().remove("user_id").apply()
    }
}