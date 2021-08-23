package com.example.myscore.domain.usecase

import com.example.myscore.data.model.CoachingSummary
import com.example.myscore.data.model.CreditReportInfo
import com.example.myscore.data.model.Score
import com.example.myscore.domain.mapper.ScoreMapper
import com.example.myscore.domain.uimodel.CoachingSummaryUI
import com.example.myscore.domain.uimodel.CreditReportInfoUI
import com.example.myscore.domain.uimodel.ScoreUI
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class ScoreMapperTest {
    private lateinit var sut: ScoreMapper

    @Before
    fun setUp() {
        sut = ScoreMapper()
    }

    @Test
    fun mapInputTest() {
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
            accountIDVStatus = "PASS"
            this.creditReportInfo = creditReportInfo
            dashboardStatus = "PASS"
            personaType = "INEXPERIENCED"
            this.coachingSummary = coachingSummary
            augmentedCreditScore = null
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
            "PASS",
            creditReportInfoUI,
            "PASS",
            "INEXPERIENCED",
            coachingSummaryUI,
            ""
        )

        val result = sut.map(input)

        result.run {
            Truth.assertThat(accountIDVStatus).isEqualTo(expected.accountIDVStatus)
            Truth.assertThat(this.creditReportInfo).isEqualTo(expected.creditReportInfo)
            Truth.assertThat(dashboardStatus).isEqualTo(expected.dashboardStatus)
            Truth.assertThat(personaType).isEqualTo(expected.personaType)
            Truth.assertThat(this.coachingSummary).isEqualTo(expected.coachingSummary)
            Truth.assertThat(augmentedCreditScore).isEqualTo(expected.augmentedCreditScore)
        }
    }
}
