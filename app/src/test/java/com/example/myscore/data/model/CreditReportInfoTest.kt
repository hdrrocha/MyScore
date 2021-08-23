package com.example.myscore.data.model

import com.example.myscore.BaseUnitTest
import com.google.common.truth.Truth
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
@RunWith(JUnit4::class)
class CreditReportInfoTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = CreditReportInfo()
        Truth.assertThat(model.score).isNull()
        Truth.assertThat(model.scoreBand).isNull()
        Truth.assertThat(model.clientRef).isNull()
        Truth.assertThat(model.status).isNull()
        Truth.assertThat(model.maxScoreValue).isNull()
        Truth.assertThat(model.minScoreValue).isNull()
        Truth.assertThat(model.monthsSinceLastDefaulted).isNull()
        Truth.assertThat(model.hasEverDefaulted).isNull()
        Truth.assertThat(model.monthsSinceLastDelinquent).isNull()
        Truth.assertThat(model.hasEverBeenDelinquent).isNull()
        Truth.assertThat(model.percentageCreditUsed).isNull()
        Truth.assertThat(model.percentageCreditUsedDirectionFlag).isNull()
        Truth.assertThat(model.changedScore).isNull()
        Truth.assertThat(model.currentShortTermDebt).isNull()
        Truth.assertThat(model.currentShortTermNonPromotionalDebt).isNull()
        Truth.assertThat(model.currentShortTermCreditLimit).isNull()
        Truth.assertThat(model.currentShortTermCreditUtilisation).isNull()
        Truth.assertThat(model.changeInShortTermDebt).isNull()
        Truth.assertThat(model.currentLongTermDebt).isNull()
        Truth.assertThat(model.currentLongTermNonPromotionalDebt).isNull()
        Truth.assertThat(model.currentLongTermCreditLimit).isNull()
        Truth.assertThat(model.currentLongTermCreditUtilisation).isNull()
        Truth.assertThat(model.changeInLongTermDebt).isNull()
        Truth.assertThat(model.numPositiveScoreFactors).isNull()
        Truth.assertThat(model.numNegativeScoreFactors).isNull()
        Truth.assertThat(model.equifaxScoreBand).isNull()
        Truth.assertThat(model.equifaxScoreBandDescription).isNull()
        Truth.assertThat(model.daysUntilNextReport).isNull()
    }
}
