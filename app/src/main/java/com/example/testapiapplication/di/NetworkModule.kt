package com.example.testapiapplication.di

import com.example.testapiapplication.network.AuthInterceptor
import com.example.testapiapplication.network.RetrofitAPI
import com.example.testapiapplication.network.TokenRefreshInterceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_1 = "https://api-staging.gofa.vn"
private const val BASE_URL_2 = "https://api-prod.gofa.vn"

val networkModule = module {

    single { AuthInterceptor(get()) }

    single { TokenRefreshInterceptor(get()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .addInterceptor(get<TokenRefreshInterceptor>())
            .build()
    }

    single(named("Retrofit1")) {
        Retrofit.Builder()
            .baseUrl(BASE_URL_1)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("Retrofit2")) {
        Retrofit.Builder()
            .baseUrl(BASE_URL_2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<RetrofitAPI>(named("Retrofit1API")){ get<Retrofit>(named("Retrofit1")).create(RetrofitAPI::class.java) }
    single<RetrofitAPI>(named("Retrofit2API")) { get<Retrofit>(named("Retrofit2")).create(RetrofitAPI::class.java) }
}