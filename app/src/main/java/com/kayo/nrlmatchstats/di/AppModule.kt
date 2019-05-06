package com.kayo.nrlmatchstats.di

import com.kayo.nrlmatchstats.matchstats.repository.MatchStatsRepository
import com.kayo.nrlmatchstats.matchstats.repository.MatchStatsRepositoryImpl
import com.kayo.nrlmatchstats.matchstats.viewmodel.MatchStatsViewModel
import com.kayo.nrlmatchstats.playerstats.repository.PlayerStatsRepository
import com.kayo.nrlmatchstats.playerstats.repository.PlayerStatsRepositoryImpl
import com.kayo.nrlmatchstats.playerstats.viewmodel.PlayerStatsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin main module
 * add the ViewModels, Repositories, Persistence to be injected here
 */


val MatchStatsAppModule  = module {

    // MatchStatsViewModel Injection
    viewModel { MatchStatsViewModel(get()) }

    // single instance of MatchStatsRepository
    single<MatchStatsRepository> { MatchStatsRepositoryImpl() }

    // PlayerStatsViewModel Injection
    viewModel { PlayerStatsViewModel(get()) }

    // single instance of PlayerStatsRepository
    single<PlayerStatsRepository> { PlayerStatsRepositoryImpl() }

}