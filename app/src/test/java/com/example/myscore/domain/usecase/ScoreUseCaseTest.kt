package com.example.myscore.domain.usecase

import com.example.myscore.data.model.CoachingSummary
import com.example.myscore.data.model.CreditReportInfo
import com.example.myscore.data.model.Score
import com.example.myscore.data.repository.abs.ScoreRepositoryAbs
import com.example.myscore.domain.mapper.abs.ScoreMapperAbs
import com.example.myscore.domain.uimodel.CoachingSummaryUI
import com.example.myscore.domain.uimodel.CreditReportInfoUI
import com.example.myscore.domain.uimodel.ScoreUI
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ScoreUseCaseTest {
    private lateinit var sut: ScoreUseCase
    private lateinit var repository: ScoreRepositoryAbs
    private lateinit var mapper: ScoreMapperAbs

    @Before
    fun setUp() {
        repository = mockkClass(ScoreRepositoryAbs::class)
        mapper = mockkClass(ScoreMapperAbs::class)
        sut = ScoreUseCase(repository, mapper)
    }

    @Test
    fun getScoreSuccessTest() = runBlocking {

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

        val input = Score().apply {
            accountIDVStatus = "Test"
            this.creditReportInfo = creditReportInfo
            dashboardStatus = ""
            personaType = ""
            this.coachingSummary = coachingSummary
            augmentedCreditScore = ""
        }

        val creditReportInfoUI = CreditReportInfoUI(
            score = 514.0,
            scoreBand = 4.0,
            clientRef = "CS-SED-655426-708782",
            status = "MATCH",
            maxScoreValue = 700.0,
            minScoreValue = 0.0,
            monthsSinceLastDefaulted = -1.0,
            hasEverDefaulted = false,
            monthsSinceLastDelinquent = 1.0,
            hasEverBeenDelinquent = true,
            percentageCreditUsed = 44.0,
            percentageCreditUsedDirectionFlag = 1.0,
            changedScore = 0.0,
            currentShortTermDebt = 13758.0,
            currentShortTermNonPromotionalDebt = 13758.0,
            currentShortTermCreditLimit = 30600.0,
            currentShortTermCreditUtilisation = 44.0,
            changeInShortTermDebt = 549.0,
            currentLongTermDebt = 24682.0,
            currentLongTermNonPromotionalDebt = 24682.0,
            currentLongTermCreditLimit = "",
            currentLongTermCreditUtilisation = "",
            changeInLongTermDebt = -327.0,
            numPositiveScoreFactors = 9.0,
            numNegativeScoreFactors = 0.0,
            equifaxScoreBand = 4.0,
            equifaxScoreBandDescription = "Excellent",
            daysUntilNextReport = 9.0,
        )

        val coachingSummaryUI = CoachingSummaryUI(
            activeTodo = false,
            activeChat = true,
            numberOfTodoItems = 0.0,
            numberOfCompletedTodoItems = 0.0,
            selected = true,
        )

        val expected = ScoreUI(
            "Test",
            creditReportInfoUI,
            "",
            "",
            coachingSummaryUI,
            ""
        )

        coEvery { repository.getScore() }.returns(input)
        every { mapper.map(any()) }.returns(expected)

        val result = sut.getScore()
        coVerify { repository.getScore() }

        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun getScoreErrorTest() {
        coEvery { repository.getScore() }.throws(Exception())
        runBlocking { sut.getScore() }
    }
}
