package com.kayo.nrlmatchstats.matchstats.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.kayo.nrlmatchstats.R
import com.kayo.nrlmatchstats.matchstats.viewmodel.MatchStatsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchStatsActivity : AppCompatActivity() {

    private val matchStatsViewModel : MatchStatsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matchstats)

        matchStatsViewModel.fetchMatchStats()
        matchStatsViewModel.matchStatsLiveData.observe(this, Observer { matchStats ->
            matchStats.forEach {
                println("match stats data on the view ---> $it")
            }
        })
    }
}
