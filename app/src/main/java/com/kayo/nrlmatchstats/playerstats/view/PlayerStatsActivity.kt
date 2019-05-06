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

        //games
        If(playerStats.career_stats.games) {
            val games = playerStats.career_stats.games.toString()
            textViewGames.text = games
        } Else {
            textViewGames.text = "Not Available"
        }

        //tries
        If(playerStats.career_stats.tries) {
            val tries = playerStats.career_stats.tries.toString()
            textViewTries.text = tries
        } Else {
            textViewTries.text = "Not Available"
        }

        //points
        If(playerStats.career_stats.points) {
            val points = playerStats.career_stats.points.toString()
            textViewPoints.text = points
        } Else {
            textViewPoints.text = "Not Available"
        }

        //win_percentages
        If(playerStats.career_stats.win_percentage) {
            val winPercentage = playerStats.career_stats.win_percentage.toString()
            textViewWinPercent.text = winPercentage
        } Else {
            textViewWinPercent.text = "Not Available"
        }

        If(playerStats.last_match_stats.errors) {
            val errors = playerStats.last_match_stats.errors.toString()
            textViewErrors.text = errors
        } Else {
            textViewErrors.text = "Not Available"
        }

        If(playerStats.last_match_stats.fantasy_points) {
            val fantasyPoints = playerStats.last_match_stats.fantasy_points.toString()
            textViewFantPoints.text = fantasyPoints
        } Else {
            textViewFantPoints.text = "Not Available"
        }

        If(playerStats.last_match_stats.kicks) {
            val kicks = playerStats.last_match_stats.kicks.toString()
            textViewKicks.text = kicks
        } Else {
            textViewKicks.text = "Not Available"
        }

        If(playerStats.last_match_stats.kick_metres) {
            val kickMeters = playerStats.last_match_stats.kick_metres.toString()
            textViewKicksMeter.text  = kickMeters
        } Else {
            textViewKicksMeter.text = "Not Available"
        }

        If(playerStats.last_match_stats.mins_played) {
            val minsPlayed = playerStats.last_match_stats.mins_played.toString()
            textViewMinsPlayed.text = minsPlayed
        } Else {
            textViewMinsPlayed.text = "Not Available"
        }
    }

    inner class If<T>(val any: T?, private val i: (T) -> Unit) {
        infix fun Else(e: () -> Unit) {
            if (any == null) e()
            else i(any)
        }
    }
}