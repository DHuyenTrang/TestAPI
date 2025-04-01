package com.example.testapiapplication

import android.app.Application
import com.example.testapiapplication.di.appModule
import com.example.testapiapplication.di.networkModule
import com.example.testapiapplication.di.repositoryModule
import com.example.testapiapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule, appModule))
        }
    }
}