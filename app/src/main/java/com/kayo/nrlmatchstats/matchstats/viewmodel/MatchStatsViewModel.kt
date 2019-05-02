package com.kayo.nrlmatchstats.matchstats.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kayo.nrlmatchstats.matchstats.model.StatsInfo
import com.kayo.nrlmatchstats.matchstats.repository.MatchStatsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * ViewModel class for the top player's match statistics screen
 */
class MatchStatsViewModel(private val repository: MatchStatsRepository, application: Application) :
    AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    val matchStatsLiveData = MutableLiveData<List<StatsInfo>>()

    fun fetchMatchStats() {
        scope.launch {
            repository.getMatchStatistics()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    matchStatsLiveData.value = it
                    matchStatsLiveData.postValue(it)
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