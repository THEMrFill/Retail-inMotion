package com.retailinmotion.philip.arnold.di

import com.retailinmotion.philip.arnold.repo.*
import com.retailinmotion.philip.arnold.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModulesMockMorning = module {
    // factories for Koin
    single<ForecastRepository> { ForecastRepositoryImplMorningMock() }
    single<TimeRepository> { TimeRepositoryImplMorningMock() }

    // viewModel for Koin
    viewModel { MainViewModel(get(), get()) }
}

val appModulesMockAfternoon = module {
    // factories for Koin
    single<ForecastRepository> { ForecastRepositoryImplAfternoonMock() }
    single<TimeRepository> { TimeRepositoryImplAfternoonMock() }

    // viewModel for Koin
    viewModel { MainViewModel(get(), get()) }
}
