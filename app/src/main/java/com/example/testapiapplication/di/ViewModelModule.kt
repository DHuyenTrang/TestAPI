package com.example.testapiapplication.di

import com.example.testapiapplication.ui.auth.SignInViewModel
import com.example.testapiapplication.ui.home.HomeViewModel
import com.example.testapiapplication.ui.profile.ProfileViewModel
import com.example.testapiapplication.ui.reverse.DataReverseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignInViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DataReverseViewModel(get()) }

}