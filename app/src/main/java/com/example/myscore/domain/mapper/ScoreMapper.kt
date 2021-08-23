package com.example.myscore.domain.mapper

import com.example.myscore.data.model.CoachingSummary
import com.example.myscore.data.model.CreditReportInfo
import com.example.myscore.data.model.Score
import com.example.myscore.domain.uimodel.CoachingSummaryUI
import com.example.myscore.domain.uimodel.CreditReportInfoUI
import com.example.myscore.domain.uimodel.ScoreUI
import com.example.myscore.domain.mapper.abs.ScoreMapperAbs

class ScoreMapper : ScoreMapperAbs {
    override fun map(input: Score) = ScoreUI(
        input.accountIDVStatus.orEmpty(),
        mapCreditReportInfo(input.creditReportInfo),
        input.dashboardStatus.orEmpty(),
        input.personaType.orEmpty(),
        mapCCoachingSummary(input.coachingSummary),
        input.augmentedCreditScore.orEmpty()
    )

    private fun mapCCoachingSummary(coachingSummary: CoachingSummary?): CoachingSummaryUI {
        return CoachingSummaryUI(
            coachingSummary?.activeTodo ?: false,
            coachingSummary?.activeChat ?: false,
            coachingSummary?.numberOfTodoItems ?: 0.0,
            coachingSummary?.numberOfCompletedTodoItems ?: 0.0,
            coachingSummary?.selected ?: false
        )

    }

    private fun mapCreditReportInfo(creditReportInfo: CreditReportInfo?): CreditReportInfoUI {
        return CreditReportInfoUI(
            creditReportInfo?.score ?: 0.0,
            creditReportInfo?.scoreBand ?: 0.0,
            creditReportInfo?.clientRef.orEmpty(),
            creditReportInfo?.status.orEmpty(),
            creditReportInfo?.maxScoreValue ?: 0.0,
            creditReportInfo?.minScoreValue ?: 0.0,
            creditReportInfo?.monthsSinceLastDefaulted ?: 0.0,
            creditReportInfo?.hasEverDefaulted ?: false,
            creditReportInfo?.monthsSinceLastDelinquent ?: 0.0,
            creditReportInfo?.hasEverBeenDelinquent ?: false,
            creditReportInfo?.percentageCreditUsed ?: 0.0,
            creditReportInfo?.percentageCreditUsedDirectionFlag ?: 0.0,
            creditReportInfo?.changedScore ?: 0.0,
            creditReportInfo?.currentShortTermDebt ?: 0.0,
            creditReportInfo?.currentShortTermNonPromotionalDebt ?: 0.0,
            creditReportInfo?.currentShortTermCreditLimit ?: 0.0,
            creditReportInfo?.currentShortTermCreditUtilisation ?: 0.0,
            creditReportInfo?.changeInShortTermDebt ?: 0.0,
            creditReportInfo?.currentLongTermDebt ?: 0.0,
            creditReportInfo?.currentLongTermNonPromotionalDebt ?: 0.0,
            creditReportInfo?.currentLongTermCreditLimit.orEmpty(),
            creditReportInfo?.currentLongTermCreditUtilisation.orEmpty(),
            creditReportInfo?.changeInLongTermDebt ?: 0.0,
            creditReportInfo?.numPositiveScoreFactors ?: 0.0,
            creditReportInfo?.numNegativeScoreFactors ?: 0.0,
            creditReportInfo?.equifaxScoreBand ?: 0.0,
            creditReportInfo?.equifaxScoreBandDescription.orEmpty(),
            creditReportInfo?.daysUntilNextReport ?: 0.0,
        )
    }
}