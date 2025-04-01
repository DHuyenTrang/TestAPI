package com.example.testapiapplication.network

import android.util.Log
import com.example.testapiapplication.TokenManager
import com.example.testapiapplication.data.request.RefreshTokenRequest
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TokenRefreshInterceptor(
    private val tokenManager: TokenManager,
) : Interceptor, KoinComponent {
    private val retrofitAPI: RetrofitAPI by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        Log.d("AUTH", "${tokenManager.getRefreshToken()}")
        Log.d("AUTH", "${response.code}")

        if (response.code == 401) { // Unauthorized -> Refresh token
            synchronized(this) {
                response.close()
                val newToken = refreshToken()
                return if (newToken != null) {
                    val newRequest = request.newBuilder()
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
            val response = retrofitAPI.getRefreshToken(RefreshTokenRequest(refreshToken)).execute()
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
