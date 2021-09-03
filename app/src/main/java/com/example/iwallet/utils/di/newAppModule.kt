package com.example.iwallet.utils.di

import android.content.Context
import com.example.iwallet.R
import com.example.iwallet.features.intro.repository.*
import com.example.iwallet.features.intro.viewmodel.*
import com.example.iwallet.features.resume.repository.AddOrSubtractProductRepository
import com.example.iwallet.features.resume.repository.DescriptionNewProductRepository
import com.example.iwallet.features.resume.repository.DescriptionProductRepository
import com.example.iwallet.features.resume.repository.ResumeRepository
import com.example.iwallet.features.resume.viewmodel.AddOrSubtractProductViewModel
import com.example.iwallet.features.resume.viewmodel.DescriptionNewProductViewModel
import com.example.iwallet.features.resume.viewmodel.DescriptionProductViewModel
import com.example.iwallet.features.resume.viewmodel.ResumeViewModel
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

    viewModel{
        RegistrationViewModel(get())
    }

    viewModel {
        ResumeViewModel(get())
    }

    viewModel {
        DescriptionNewProductViewModel(get())
    }

    viewModel {
        AddOrSubtractProductViewModel(get())
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
        RegistrationRepository(get())
    }

    factory {
        ResumeRepository(get())
    }

    factory {
        DescriptionNewProductRepository(get())
    }

    factory {
        AddOrSubtractProductRepository(get())
    }

    factory {
        DescriptionProductRepository(get())
    }

    factory {
        ExtractRepository(get())
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