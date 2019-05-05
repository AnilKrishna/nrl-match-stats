package com.kayo.nrlmatchstats.matchstats.model

data class StatsInfo(
    val match_id: String,
    val stat_type: String,
    val team_A: Team,
    val team_B: Team
)