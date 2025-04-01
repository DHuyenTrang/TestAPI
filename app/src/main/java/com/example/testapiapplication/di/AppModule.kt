package com.example.testapiapplication.di

import android.content.Context
import android.content.SharedPreferences
import com.example.testapiapplication.TokenManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module{

    single<SharedPreferences> { androidContext().getSharedPreferences("authPrefs", Context.MODE_PRIVATE) }
    single { TokenManager(androidContext()) }

}