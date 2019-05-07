package com.kayo.nrlmatchstats.matchstats.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kayo.nrlmatchstats.matchstats.model.StatsInfo
import com.kayo.nrlmatchstats.matchstats.repository.MatchStatsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * ViewModel class for the top player's match statistics screen
 */
class MatchStatsViewModel(private val repository: MatchStatsRepository) :
    ViewModel() {

    private val viewModelJob = Job()
    private val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.IO

    private val scope = CoroutineScope(coroutineContext)

    val matchStatsLiveData = MutableLiveData<List<StatsInfo>>()

    fun fetchMatchStats() {
        scope.launch {
            repository.getMatchStatistics()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { error ->
                    println("MatchStats Data retrieve Error in ViewModel ---> ${error.message} ")
                }
                .subscribe { it ->
                    matchStatsLiveData.value = it
                    it.forEach {
                        println("MatchStat Data in ViewModel ---> $it ")
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}