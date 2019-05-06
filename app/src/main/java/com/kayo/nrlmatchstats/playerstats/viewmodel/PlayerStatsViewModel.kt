package com.kayo.nrlmatchstats.playerstats.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kayo.nrlmatchstats.playerstats.model.PlayerStats
import com.kayo.nrlmatchstats.playerstats.repository.PlayerStatsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PlayerStatsViewModel(private val repository: PlayerStatsRepository)
    : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    val playerInfoLiveData = MutableLiveData<PlayerStats>()


    fun fetchPlayerDetails(playerId: Int, teamId: Int) {
        scope.launch {
            repository.getPlayerStats(teamId,playerId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { error ->
                    println("PlayerStats Data retrieve Error in ViewModel ---> ${error.message} ")
                }
                .subscribe { it ->
                    playerInfoLiveData.value = it
                    println("PlayerStats Data in ViewModel ---> $it ")
                }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}