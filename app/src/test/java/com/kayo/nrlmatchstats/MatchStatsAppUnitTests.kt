package com.kayo.nrlmatchstats

import com.kayo.nrlmatchstats.di.MatchStatsAppModule
import com.kayo.nrlmatchstats.matchstats.repository.MatchStatsRepository
import com.kayo.nrlmatchstats.matchstats.viewmodel.MatchStatsViewModel
import com.kayo.nrlmatchstats.playerstats.repository.PlayerStatsRepository
import com.kayo.nrlmatchstats.playerstats.viewmodel.PlayerStatsViewModel
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class MatchStatsAppUnitTests : KoinTest {

    private val matchStatsRepository: MatchStatsRepository by inject()
    private val matchStatsViewModel by inject<MatchStatsViewModel>()
    private val playerStatsRepository: PlayerStatsRepository by inject()
    private val playerStatsViewModel by inject<PlayerStatsViewModel>()

    @Before
    fun before() {
        startKoin {
            modules(MatchStatsAppModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    /**
     * DI Tests
     */

    @Test
    fun testShouldInjectMatchStatsViewModel() {
        assertNotNull(matchStatsViewModel)
    }

    @Test
    fun testShouldInjectMatchStatsRepository() {
        assertNotNull(matchStatsRepository)
    }

    @Test
    fun testShouldInjectPlayerStatsViewModel() {
        assertNotNull(playerStatsViewModel)
    }

    @Test
    fun testShouldInjectPlayerStatsRepository() {
        assertNotNull(playerStatsRepository)
    }

    /**
     * Data source / Repository Tests
     */

    @Test
    fun testGetMatchStats() {
        val test = matchStatsRepository.getMatchStatistics().test()
        test.awaitTerminalEvent()
        test.assertComplete()
    }

    @Test
    fun testGetMatchStatsDataSet() {
        val test = matchStatsRepository.getMatchStatistics().test()
        test.awaitTerminalEvent()
        test.assertValue { list -> list.isNotEmpty() }
    }

    @Test
    fun testGetPlayerStats() {
        val teamId  = 55011
        val playerId = 108392
        val test = playerStatsRepository.getPlayerStats(teamId,playerId).test()
        test.awaitTerminalEvent()
        test.assertComplete()
    }

    @Test
    fun testGetPlayerStatsDataSet() {
        val teamId  = 55011
        val playerId = 108392
        val test = playerStatsRepository.getPlayerStats(teamId,playerId).test()
        test.awaitTerminalEvent()
        test.assertValue { stats -> true }
    }
}