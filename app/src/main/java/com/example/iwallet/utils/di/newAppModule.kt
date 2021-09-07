package com.example.iwallet.utils.di

import android.content.Context
import com.example.iwallet.R
import com.example.iwallet.features.intro.repository.LoginRepository
import com.example.iwallet.features.intro.repository.OnbordingViewModel
import com.example.iwallet.features.intro.repository.RegistrationRepository
import com.example.iwallet.features.intro.repository.SplashRepository
import com.example.iwallet.features.intro.viewmodel.LoginViewModel
import com.example.iwallet.features.intro.viewmodel.OnbordingRepository
import com.example.iwallet.features.intro.viewmodel.RegistrationViewModel
import com.example.iwallet.features.intro.viewmodel.SplashViewModel
import com.example.iwallet.features.resume.repository.*
import com.example.iwallet.features.resume.viewmodel.*
import com.example.iwallet.features.wallet.repository.ExtractRepository
import com.example.iwallet.features.wallet.viewmodel.ExtractViewModel
import com.example.iwallet.utils.data.local.SessionManager
import com.example.iwallet.utils.data.local.database.AppDatabase
import com.example.iwallet.utils.data.remote.ApiService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newAppModule = module {
    viewModel {
        SplashViewModel(get())
    }

    viewModel {
        OnbordingViewModel(get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        RegistrationViewModel(get())
    }

    viewModel {
        ResumeViewModel(get())
    }

    viewModel {
        NewsViewModel(get())
    }

    viewModel {
        DescriptionNewProductViewModel(get())
    }

    viewModel {
        ListProductsViewModel(get())
    }

    viewModel {
        DescriptionProductViewModel(get())
    }

    viewModel {
        ExtractViewModel(get())
    }

    factory {
        SplashRepository(get())
    }

    factory {
        OnbordingRepository(get())
    }

    factory {
        LoginRepository(get())
    }

    factory {
        RegistrationRepository()
    }

    factory {
        ResumeRepository(get())
    }

    factory {
        NewsRepository(get())
    }

    factory {
        DescriptionNewProductRepository(get(), get())
    }

    factory {
        ListProductsRepository(get())
    }

    factory {
        DescriptionProductRepository(get(), get())
    }

    factory {
        ExtractRepository(get())
    }

    single {
        AppDatabase.getInstance(androidContext()).extractDAO()
    }

    single {
        AppDatabase.getInstance(androidContext()).productDAO()
    }

    single {
        ApiService.getEndPointInstance()
    }

    single {
        SessionManager(get())
    }

    single {
        androidContext()
            .getSharedPreferences(R.string.preference_file_key.toString(), Context.MODE_PRIVATE)
    }
}