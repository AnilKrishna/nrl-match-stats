package com.kayo.nrlmatchstats

import com.kayo.nrlmatchstats.matchstats.model.StatsInfo
import com.kayo.nrlmatchstats.playerstats.model.PlayerStats
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API Endpoints interface
 */
interface MatchStatsApiClient {

    @GET("matches/NRL20172101/topplayerstats.json;type=fantasy_points;type=tackles;type=runs;type=run_metres?limit=5&userkey=${BuildConfig.USER_API_KEY}")
    fun getMatchStats() : Deferred<Response<List<StatsInfo>>>


    @GET("series/1/seasons/115/teams/{team-id}/players/{player-id}/detailedstats.json?userkey=${BuildConfig.USER_API_KEY}")
    fun getPlayerStats(@Path("team-id") teamId: String, @Path("player-id") playerId: String) : Deferred<Response<List<PlayerStats>>>

}

