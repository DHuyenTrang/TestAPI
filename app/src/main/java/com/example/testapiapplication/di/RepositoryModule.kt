package com.example.testapiapplication.di

import com.example.testapiapplication.repository.AuthRepository
import com.example.testapiapplication.repository.DataPointRepository
import com.example.testapiapplication.repository.DataReverseRepository
import com.example.testapiapplication.repository.ProfileRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get(named("Retrofit1API"))) }
    single { DataPointRepository(get(named("Retrofit1API"))) }
    single { DataReverseRepository(get(named("Retrofit2API"))) }
    single { ProfileRepository(get(named("Retrofit1API"))) }
}