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
    private const val BASE_URL_1 = "https://api-staging.gofa.vn"
    private const val BASE_URL_2 = "https://api-prod.gofa.vn"

    @BaseUrl1
    @Provides
    @Singleton
    fun provideRetrofit1(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_1)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @BaseUrl2
    @Provides
    @Singleton
    fun provideRetrofit2(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @BaseUrl1
    @Provides
    @Singleton
    fun provideRetrofitAPI1(@BaseUrl1 retrofit: Retrofit): RetrofitAPI {
        return retrofit.create(RetrofitAPI::class.java)
    }

    @BaseUrl2
    @Provides
    @Singleton
    fun provideRetrofitAPI2(@BaseUrl2 retrofit: Retrofit): RetrofitAPI {
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