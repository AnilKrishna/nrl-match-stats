package com.kayo.nrlmatchstats.playerstats.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.kayo.nrlmatchstats.R
import com.kayo.nrlmatchstats.playerstats.model.PlayerStats
import com.kayo.nrlmatchstats.playerstats.viewmodel.PlayerStatsViewModel
import kotlinx.android.synthetic.main.activity_player_info_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerStatsActivity :  AppCompatActivity() {

    private val playerStatsViewModel : PlayerStatsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_info_details)

        playerStatsViewModel.fetchPlayerDetails(intent.getIntExtra("playerid",0), intent.getIntExtra("teamid",0))
        playerStatsViewModel.playerInfoLiveData.observe(this, Observer { playerStats ->
            println("player stats data on the view ---> $playerStats")
            updatePlayerDetailsView(playerStats)
        })
    }

    private fun updatePlayerDetailsView(playerStats: PlayerStats) {
        playerNameTextView.text = playerStats.full_name
        Glide.with(this@PlayerStatsActivity)
            .load("http://media.foxsports.com.au/match-centre/includes/images/headshots/nrl/${playerStats.id}.jpg")
            .placeholder(R.drawable.player_headshot_blank_large)
            .into(profileImageView)
        positionTextView.text = playerStats.position

        val tv_error = TextView(this)
        tv_error.textSize = 20f
        tv_error.text = "Errors -->" + playerStats.last_match_stats.errors
        lastMatchStats.addView(tv_error)

        val tv_mins_played = TextView(this)
        tv_mins_played.textSize = 20f
        tv_mins_played.text = "Mins Played -->" + playerStats.last_match_stats.mins_played
        lastMatchStats.addView(tv_mins_played)

        val tv_fantasy_points = TextView(this)
        tv_fantasy_points.textSize = 20f
        tv_fantasy_points.text = "Fantasy Points -->" + playerStats.last_match_stats.fantasy_points
        lastMatchStats.addView(tv_fantasy_points)

        val tv_kick_meters = TextView(this)
        tv_kick_meters.textSize = 20f
        tv_kick_meters.text = "Kick Meters -->" + playerStats.last_match_stats.kick_metres
        lastMatchStats.addView(tv_kick_meters)

        val tv_kicks = TextView(this)
        tv_kicks.textSize = 20f
        tv_kicks.text = "Kicks -->" + playerStats.last_match_stats.kicks
        lastMatchStats.addView(tv_kicks)
    }
}