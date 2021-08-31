package com.example.myscore.data.repository

import com.example.myscore.data.api.ScoreApi
import com.example.myscore.data.model.CoachingSummary
import com.example.myscore.data.model.CreditReportInfo
import com.example.myscore.data.model.Score
import com.example.myscore.data.repository.abs.ScoreRepositoryAbs
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ScoreRepositoryTest {
    private lateinit var mockApi: ScoreApi
    private lateinit var sut: ScoreRepositoryAbs

    @Before
    fun start() {
        mockApi = mockkClass(ScoreApi::class)
        sut = ScoreRepository(mockApi)
    }

    @Test
    fun getScore() = runBlocking {

        val creditReportInfo = CreditReportInfo().apply {
            score = 514.0
            scoreBand = 4.0
            clientRef = "CS-SED-655426-708782"
            status = "MATCH"
            maxScoreValue = 700.0
            minScoreValue = 0.0
            monthsSinceLastDefaulted = -1.0
            hasEverDefaulted = false
            monthsSinceLastDelinquent = 1.0
            hasEverBeenDelinquent = true
            percentageCreditUsed = 44.0
            percentageCreditUsedDirectionFlag = 1.0
            changedScore = 0.0
            currentShortTermDebt = 13758.0
            currentShortTermNonPromotionalDebt = 13758.0
            currentShortTermCreditLimit = 30600.0
            currentShortTermCreditUtilisation = 44.0
            changeInShortTermDebt = 549.0
            currentLongTermDebt = 24682.0
            currentLongTermNonPromotionalDebt = 24682.0
            currentLongTermCreditLimit = null
            currentLongTermCreditUtilisation = null
            changeInLongTermDebt = -327.0
            numPositiveScoreFactors = 9.0
            numNegativeScoreFactors = 0.0
            equifaxScoreBand = 4.0
            equifaxScoreBandDescription = "Excellent"
            daysUntilNextReport = 9.0
        }

        val coachingSummary = CoachingSummary().apply {
            activeTodo = false
            activeChat = true
            numberOfTodoItems = 0.0
            numberOfCompletedTodoItems = 0.0
            selected = true
        }

        val expected = Score().apply {
            accountIDVStatus = "PASS"
            this.creditReportInfo = creditReportInfo
            dashboardStatus = "PASS"
            personaType = "INEXPERIENCED"
            this.coachingSummary = coachingSummary
            augmentedCreditScore = null
        }

        coEvery { mockApi.getScore() }.returns(expected)
        val result = sut.getScore()
        coVerify { mockApi.getScore() }

        Truth.assertThat(result.accountIDVStatus).isEqualTo(expected.accountIDVStatus)
        Truth.assertThat(result.creditReportInfo).isEqualTo(expected.creditReportInfo)
        Truth.assertThat(result.dashboardStatus).isEqualTo(expected.dashboardStatus)
        Truth.assertThat(result.personaType).isEqualTo(expected.personaType)
        Truth.assertThat(result.coachingSummary).isEqualTo(expected.coachingSummary)
        Truth.assertThat(result.augmentedCreditScore).isEqualTo(expected.augmentedCreditScore)
    }
}
