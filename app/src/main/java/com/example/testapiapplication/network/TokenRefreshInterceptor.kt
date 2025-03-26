package com.example.testapiapplication.network

import android.util.Log
import com.example.testapiapplication.TokenManager
import com.example.testapiapplication.repository.AuthRepository
import com.example.testapiapplication.request.RefreshTokenRequest
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Provider

class TokenRefreshInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
    private val retrofitAPIProvider: Provider<RetrofitAPI>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code == 401) { // Unauthorized -> Refresh token
            synchronized(this) {
                response.close()
                val newToken = refreshToken()
                return if (newToken != null) {
                    val newRequest = request.newBuilder()
                        .addHeader("Authorization", "Bearer $newToken")
                        .build()
                    chain.proceed(newRequest)
                } else {
                    response
                }
            }
        }
        return response
    }

    private fun refreshToken(): String? {
        val refreshToken = tokenManager.getRefreshToken() ?: return null

        return try {
            val retrofitAPI = retrofitAPIProvider.get()
            val response = retrofitAPI.getRefreshToken(RefreshTokenRequest(refreshToken)).execute() // ✅ Directly call RetrofitAPI
            if (response.isSuccessful) {
                val newTokens = response.body()!!
                Log.d("AUTH", "New access token: ${newTokens.access_token}")
                tokenManager.saveToken(newTokens.access_token, newTokens.refresh_token)
                newTokens.access_token
            } else {
                tokenManager.clearToken()
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
