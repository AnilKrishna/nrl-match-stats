package com.kayo.nrlmatchstats.matchstats.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kayo.nrlmatchstats.matchstats.repository.MatchStatsRepository

/**
 * ViewModel class for the top player's match statistics screen
 */
class MatchStatsViewModel(private val repository: MatchStatsRepository, application: Application) : AndroidViewModel(application) {



}