package com.kayo.nrlmatchstats.network

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

// Singleton pattern in Kotlin: https://kotlinlang.org/docs/reference/object-declarations.html#object-declarations
object MatchStatsApiAccess {

    val matchStatsApi : MatchStatsApiClient by lazy {
        Log.d("WebAccess", "Creating retrofit client")

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            // The NRL API endpoint - base URL
            .baseUrl("https://statsapi.foxsports.com.au/3.0/api/sports/league/")
            // Logging interceptor
            .client(client)
            // for strings and both primitives and their boxed types to text/plain bodies
            .addConverterFactory(ScalarsConverterFactory.create())
            // Moshi maps JSON to classes
            .addConverterFactory(MoshiConverterFactory.create())
            // The call adapter handles threads
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        // Create Retrofit client
        return@lazy retrofit.create(MatchStatsApiClient::class.java)
    }
}