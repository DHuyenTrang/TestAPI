package com.example.testapiapplication.network

import com.example.testapiapplication.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager)
    : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = tokenManager.getAccessToken() ?: ""
        val userID = tokenManager.getUserId() ?: ""
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("x-id", userID)// add header author
            .build()
        return chain.proceed(request) // tiep tuc gui request da sua
    }

}