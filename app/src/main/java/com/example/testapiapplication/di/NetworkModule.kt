package com.example.testapiapplication.di

import com.example.testapiapplication.network.AuthInterceptor
import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.network.TokenRefreshInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val BASE_URL = "https://api-staging.gofa.vn"

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitAPI(retrofit: Retrofit): RetrofitAPI {
        return retrofit.create(RetrofitAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        tokenRefreshInterceptor: TokenRefreshInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(tokenRefreshInterceptor)
            .build()
    }
}