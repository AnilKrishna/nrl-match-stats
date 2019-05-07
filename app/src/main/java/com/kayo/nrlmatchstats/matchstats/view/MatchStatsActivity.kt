package com.kayo.nrlmatchstats.matchstats.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kayo.nrlmatchstats.R
import com.kayo.nrlmatchstats.matchstats.viewmodel.MatchStatsViewModel
import com.kayo.nrlmatchstats.playerstats.view.PlayerStatsActivity
import kotlinx.android.synthetic.main.activity_match_stats.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchStatsActivity : AppCompatActivity() {

    private val matchStatsViewModel : MatchStatsViewModel by viewModel()

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_stats)

        matchStatsViewModel.fetchMatchStats()
        matchStatsViewModel.matchStatsLiveData.observe(this, Observer { matchStats ->
            matchStats.forEach {
                println("match stats data on the view ---> $it")
            }

            recyclerView = rv_match_stats
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MatchStatsActivity, RecyclerView.VERTICAL, false)
                adapter = StatsInfoAdapter(matchStats)
            }
        })
    }

    fun playerInfoDetails(playerId: Int, teamID: Int) {
        println("Player details activity call PLAYER: ---> $playerId")
        println("Player details activity call TEAM ID: ---> $teamID")
        val intent = Intent(this@MatchStatsActivity,PlayerStatsActivity::class.java);
        intent.putExtra("playerid", playerId)
        intent.putExtra("teamid", teamID)
        startActivity(intent)
    }
}
