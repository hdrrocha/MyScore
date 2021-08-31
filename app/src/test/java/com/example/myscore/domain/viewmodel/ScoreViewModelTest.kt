package com.example.myscore.domain.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myscore.CoroutinesTestRule
import com.example.myscore.domain.uimodel.CoachingSummaryUI
import com.example.myscore.domain.uimodel.CreditReportInfoUI
import com.example.myscore.domain.uimodel.ScoreUI
import com.example.myscore.domain.usecase.abs.ScoreUseCaseAbs
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScoreViewModelViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutineRule = CoroutinesTestRule()

    private lateinit var sut: ScoreViewModel
    private lateinit var mockUseCase: ScoreUseCaseAbs
    private var loadingLiveData = mutableListOf<Boolean>()
    private var errorLiveData = mutableListOf<Boolean>()

    @Before
    fun setUp() {
        mockUseCase = mockkClass(ScoreUseCaseAbs::class)
        sut = ScoreViewModel(mockUseCase)
        sut.hasError.observeForever { errorLiveData.add(it) }
        sut.isLoading.observeForever { loadingLiveData.add(it) }
        loadingLiveData.clear()
        errorLiveData.clear()
    }

    @Test
    fun loadScoreDataSuccessTest() {
        coEvery { mockUseCase.getScore() }.returns(dummyResponse())
        sut.loadData()
        val result = sut.uiScore.value
        coVerify { mockUseCase.getScore() }
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(ScoreUI::class.java)
        Truth.assertThat(result?.accountIDVStatus).isEqualTo("Test")
        Truth.assertThat(result?.creditReportInfo).isEqualTo(dummyCreditReportResponse())
        Truth.assertThat(result?.dashboardStatus).isEqualTo("")
        Truth.assertThat(result?.personaType).isEqualTo("")
        Truth.assertThat(result?.coachingSummary).isEqualTo(dummyCoachingSummary())
        Truth.assertThat(result?.augmentedCreditScore).isEqualTo("")

        Truth.assertThat(loadingLiveData).containsExactly(true, false).inOrder()
        Truth.assertThat(errorLiveData).containsExactly(false).inOrder()
    }

    @Test
    fun loadScoreDataErrorTest() {
        coEvery { mockUseCase.getScore() }.throws(Exception())
        sut.loadData()
        val result = sut.uiScore.value
        coVerify { mockUseCase.getScore() }
        Truth.assertThat(result).isNull()
        Truth.assertThat(loadingLiveData).containsExactly(true, false).inOrder()
        Truth.assertThat(errorLiveData).containsExactly(false, true).inOrder()
    }

    private fun dummyResponse() = ScoreUI(
        "Test",
        dummyCreditReportResponse(),
        "",
        "",
        dummyCoachingSummary(),
        ""
    )

    private fun dummyCreditReportResponse() = CreditReportInfoUI(
        score = 514,
        scoreBand = 4.0,
        clientRef = "CS-SED-655426-708782",
        status = "MATCH",
        maxScoreValue = 700,
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

    private fun dummyCoachingSummary() = CoachingSummaryUI(
        activeTodo = false,
        activeChat = true,
        numberOfTodoItems = 0.0,
        numberOfCompletedTodoItems = 0.0,
        selected = true,
    )
}
