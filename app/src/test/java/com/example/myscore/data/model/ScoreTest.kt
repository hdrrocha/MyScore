package com.example.myscore.data.model

import com.example.myscore.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ScoreTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = Score()
        Truth.assertThat(model.accountIDVStatus).isNull()
        Truth.assertThat(model.creditReportInfo).isNull()
        Truth.assertThat(model.dashboardStatus).isNull()
        Truth.assertThat(model.personaType).isNull()
        Truth.assertThat(model.coachingSummary).isNull()
        Truth.assertThat(model.augmentedCreditScore).isNull()
    }
}
