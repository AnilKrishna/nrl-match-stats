package com.kayo.nrlmatchstats.matchstats.model

data class TopPlayer(
    val full_name: String,
    val id: Int,
    val jumper_number: Int,
    val position: String,
    val short_name: String,
    val stat_value: Int
)