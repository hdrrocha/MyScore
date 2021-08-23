package com.example.myscore.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myscore.BaseUnitTest
import com.example.myscore.data.model.CoachingSummary
import com.example.myscore.data.model.CreditReportInfo
import com.example.myscore.data.model.Score
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection

class GitHubApiTest : BaseUnitTest() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: ScoreApi

    @Before
    fun start() {
        sut = buildApi()
    }

    @Test
    fun getScoreSuccessTest() = runBlocking {
        mockNetworkResponseWithFileContent("score200.json", HttpURLConnection.HTTP_OK)

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

        val actual = sut.getScore()
        Truth.assertThat(actual.accountIDVStatus).isEqualTo(expected.accountIDVStatus)
        Truth.assertThat(actual.creditReportInfo).isEqualTo(expected.creditReportInfo)
        Truth.assertThat(actual.dashboardStatus).isEqualTo(expected.dashboardStatus)
        Truth.assertThat(actual.personaType).isEqualTo(expected.personaType)
        Truth.assertThat(actual.coachingSummary).isEqualTo(expected.coachingSummary)
        Truth.assertThat(actual.augmentedCreditScore).isEqualTo(expected.augmentedCreditScore)
    }

    @Test(expected = HttpException::class)
    fun getScoreErrorTest() {
        runBlocking {
            mockNetworkResponseWithFileContent(
                    "score200.json",
                    HttpURLConnection.HTTP_BAD_REQUEST
            )
            sut.getScore()
        }
    }
}
