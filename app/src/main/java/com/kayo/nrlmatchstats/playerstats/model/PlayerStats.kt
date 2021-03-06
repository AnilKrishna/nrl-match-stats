package com.kayo.nrlmatchstats.playerstats.model

data class PlayerStats(
    val career_stats: CareerStats,
    val date_of_birth: String,
    val full_name: String,
    val height_cm: Int,
    val id: Int,
    val last_match_id: Any,
    val last_match_stats: LastMatchStats,
    val other_names: String,
    val position: String,
    val series_season_stats: SeriesSeasonStats,
    val short_name: String,
    val surname: String,
    val weight_kg: Int
)