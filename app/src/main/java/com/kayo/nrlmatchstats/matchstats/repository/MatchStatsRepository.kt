package com.kayo.nrlmatchstats.matchstats.repository

import com.kayo.nrlmatchstats.matchstats.model.StatsInfo
import io.reactivex.Observable

/**
 * ViewModel <> Repository Implementation linkage
 */
interface MatchStatsRepository {

    fun getMatchStatistics() : Observable<List<StatsInfo>>

}