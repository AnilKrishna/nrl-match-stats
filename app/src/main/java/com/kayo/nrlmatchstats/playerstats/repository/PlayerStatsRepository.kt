package com.kayo.nrlmatchstats.playerstats.repository

import com.kayo.nrlmatchstats.playerstats.model.PlayerStats
import io.reactivex.Observable

interface PlayerStatsRepository {

    fun getPlayerStats(teamId:Int, playerId:Int) : Observable<PlayerStats>
}