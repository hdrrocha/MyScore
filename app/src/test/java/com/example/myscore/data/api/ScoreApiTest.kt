package com.example.myscore.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myscore.BaseUnitTest
import com.example.myscore.data.model.CoachingSummary
import com.example.myscore.data.model.CreditReportInfo
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

        val actual = sut.getScore()
        Truth.assertThat(actual.accountIDVStatus).isEqualTo("PASS")
        testCreditReportInfo(actual.creditReportInfo)
        Truth.assertThat(actual.dashboardStatus).isEqualTo("PASS")
        Truth.assertThat(actual.personaType).isEqualTo("INEXPERIENCED")
        testCoachingSummary(actual.coachingSummary)
        Truth.assertThat(actual.augmentedCreditScore).isEqualTo(null)
    }

    private fun testCoachingSummary(coachingSummary: CoachingSummary?) {
        Truth.assertThat(coachingSummary?.activeTodo).isEqualTo(false)
        Truth.assertThat(coachingSummary?.activeChat).isEqualTo(true)
        Truth.assertThat(coachingSummary?.numberOfTodoItems).isEqualTo(0.0)
        Truth.assertThat(coachingSummary?.numberOfCompletedTodoItems).isEqualTo(0.0)
        Truth.assertThat(coachingSummary?.selected).isEqualTo(true)
    }

    private fun testCreditReportInfo(creditReportInfo: CreditReportInfo?) {
        Truth.assertThat(creditReportInfo?.score).isEqualTo(514.0)
        Truth.assertThat(creditReportInfo?.scoreBand).isEqualTo(4.0)
        Truth.assertThat(creditReportInfo?.clientRef).isEqualTo("CS-SED-655426-708782")
        Truth.assertThat(creditReportInfo?.status).isEqualTo("MATCH")
        Truth.assertThat(creditReportInfo?.maxScoreValue).isEqualTo(700.0)
        Truth.assertThat(creditReportInfo?.minScoreValue).isEqualTo(0.0)
        Truth.assertThat(creditReportInfo?.monthsSinceLastDefaulted).isEqualTo(-1.0)
        Truth.assertThat(creditReportInfo?.hasEverDefaulted).isEqualTo(false)
        Truth.assertThat(creditReportInfo?.monthsSinceLastDelinquent).isEqualTo(1.0)
        Truth.assertThat(creditReportInfo?.hasEverBeenDelinquent).isEqualTo(true)
        Truth.assertThat(creditReportInfo?.percentageCreditUsed).isEqualTo(44.0)
        Truth.assertThat(creditReportInfo?.percentageCreditUsedDirectionFlag).isEqualTo(1.0)
        Truth.assertThat(creditReportInfo?.changedScore).isEqualTo(0.0)
        Truth.assertThat(creditReportInfo?.currentShortTermDebt).isEqualTo(13758.0)
        Truth.assertThat(creditReportInfo?.currentShortTermNonPromotionalDebt).isEqualTo(13758.0)
        Truth.assertThat(creditReportInfo?.currentShortTermCreditLimit).isEqualTo(30600.0)
        Truth.assertThat(creditReportInfo?.currentShortTermCreditUtilisation).isEqualTo(44.0)
        Truth.assertThat(creditReportInfo?.changeInShortTermDebt).isEqualTo(549.0)
        Truth.assertThat(creditReportInfo?.currentLongTermDebt).isEqualTo(24682.0)
        Truth.assertThat(creditReportInfo?.currentLongTermNonPromotionalDebt).isEqualTo(24682.0)
        Truth.assertThat(creditReportInfo?.currentLongTermCreditLimit).isEqualTo(null)
        Truth.assertThat(creditReportInfo?.currentLongTermCreditUtilisation).isEqualTo(null)
        Truth.assertThat(creditReportInfo?.changeInLongTermDebt).isEqualTo(-327.0)
        Truth.assertThat(creditReportInfo?.numPositiveScoreFactors).isEqualTo(9.0)
        Truth.assertThat(creditReportInfo?.numNegativeScoreFactors).isEqualTo(0.0)
        Truth.assertThat(creditReportInfo?.equifaxScoreBand).isEqualTo(4.0)
        Truth.assertThat(creditReportInfo?.equifaxScoreBandDescription).isEqualTo("Excellent")
        Truth.assertThat(creditReportInfo?.daysUntilNextReport).isEqualTo(9.0)
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
