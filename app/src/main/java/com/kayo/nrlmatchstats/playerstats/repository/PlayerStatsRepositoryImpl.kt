package com.kayo.nrlmatchstats.playerstats.repository

import com.kayo.nrlmatchstats.network.MatchStatsApiAccess
import com.kayo.nrlmatchstats.playerstats.model.PlayerStats
import io.reactivex.Observable

class PlayerStatsRepositoryImpl : PlayerStatsRepository {

    override fun getPlayerStats(teamId: Int, playerId: Int): Observable<PlayerStats> {
        return MatchStatsApiAccess.matchStatsApi.getPlayerStats(teamId.toString(), playerId.toString())
    }

}