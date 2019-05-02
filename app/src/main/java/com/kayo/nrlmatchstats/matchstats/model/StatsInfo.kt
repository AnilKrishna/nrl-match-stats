package com.kayo.nrlmatchstats.matchstats.model

data class StatsInfo(
    val match_id: String,
    val stat_type: String,
    val team_A: TeamA,
    val team_B: TeamB
)