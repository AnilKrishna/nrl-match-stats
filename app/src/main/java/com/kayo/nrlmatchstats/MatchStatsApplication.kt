package com.kayo.nrlmatchstats

import android.app.Application
import com.kayo.nrlmatchstats.di.MatchStatsAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MatchStatsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // koin initialisation
        startKoin {
            // Android context
            androidContext(this@MatchStatsApplication)

            //modules
            modules(MatchStatsAppModule)
        }
    }
}