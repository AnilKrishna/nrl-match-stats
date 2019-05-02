package com.kayo.nrlmatchstats.matchstats.repository

import com.kayo.nrlmatchstats.matchstats.model.StatsInfo
import com.kayo.nrlmatchstats.network.MatchStatsApiAccess
import io.reactivex.Observable

/**
 * Gateway to the VMs and APIs/Persistence
 */
class MatchStatsRepositoryImpl : MatchStatsRepository {

    override fun getMatchStatistics(): Observable<List<StatsInfo>> {
        return MatchStatsApiAccess.matchStatsApi.getMatchStats()
    }
}