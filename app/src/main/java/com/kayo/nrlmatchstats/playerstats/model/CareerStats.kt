package com.kayo.nrlmatchstats.playerstats.model

data class CareerStats(
    val games: Int,
    val points: Int,
    val tries: Int,
    val win_percentage: Double
)