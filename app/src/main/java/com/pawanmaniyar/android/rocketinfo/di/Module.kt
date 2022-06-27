package com.pawanmaniyar.android.rocketinfo.di

import com.pawanmaniyar.android.rocketinfo.data.api.core.ApiClient
import com.pawanmaniyar.android.rocketinfo.data.db.AppDatabase
import com.pawanmaniyar.android.rocketinfo.data.repository.RocketRepository
import com.pawanmaniyar.android.rocketinfo.data.repository.datasource.RocketDataSource
import com.pawanmaniyar.android.rocketinfo.ui.rockets.RocketsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val diModule = module {
    // Data
    single { ApiClient().spacexApi }

    single { RocketDataSource(get()) }
    single { RocketRepository(get(), get()) }

    // Database
    single { AppDatabase.buildDatabase(androidApplication()) }
    single { get<AppDatabase>().rocketDao() }

    // ViewModels
    viewModel { RocketsViewModel(get()) }
}