package com.kayo.nrlmatchstats.matchstats.model

data class TeamB(
    val code: String,
    val id: Int,
    val name: String,
    val short_name: String,
    val top_players: List<TopPlayer>
)