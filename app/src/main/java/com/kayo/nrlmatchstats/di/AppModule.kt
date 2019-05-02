package com.kayo.nrlmatchstats.di

import com.kayo.nrlmatchstats.matchstats.repository.MatchStatsRepository
import com.kayo.nrlmatchstats.matchstats.repository.MatchStatsRepositoryImpl
import com.kayo.nrlmatchstats.matchstats.viewmodel.MatchStatsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin main module
 * add the ViewModels, Repositories, Persistence to be injected here
 */


val MatchStatsAppModule  = module {

    // MatchStatsViewModel Injection
    viewModel { MatchStatsViewModel(get(), androidApplication()) }

    // single instance of MatchStatsRepository
    single<MatchStatsRepository> { MatchStatsRepositoryImpl() }



}