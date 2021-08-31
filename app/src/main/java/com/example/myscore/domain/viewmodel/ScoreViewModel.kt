package com.example.myscore.domain.viewmodel

import androidx.lifecycle.*
import com.example.myscore.domain.uimodel.CreditReportInfoUI
import com.example.myscore.domain.uimodel.ScoreUI
import com.example.myscore.domain.usecase.abs.ScoreUseCaseAbs
import kotlinx.coroutines.launch

class ScoreViewModel(
    private val useCase: ScoreUseCaseAbs
) : ViewModel() {
    private val _uiScore = MutableLiveData<ScoreUI>()
    val uiScore: LiveData<ScoreUI>
        get() = _uiScore

    private val error = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    val status =
        Transformations.map(_uiScore) {
            it != null && loading.equals(false) || it != null && hasError.equals(false)
        }

    val hasError get() = error as LiveData<Boolean>
    val isLoading get() = loading as LiveData<Boolean>

    val scoreLimit: LiveData<CreditReportInfoUI> =
        Transformations.map(_uiScore) { it.creditReportInfo }
    val scoreLimitTotal: LiveData<Int?> = Transformations.map(scoreLimit) {
        it.maxScoreValue.toInt()
    }
    val scoreLimitAvailable: LiveData<Int?> = Transformations.map(scoreLimit) {
        it.score.toInt()
    }
    val scoreLimitAvailableDescription: LiveData<String?> = Transformations.map(scoreLimit) {
        it.score.toString()
    }

    val scoreLimitTotalDescription: LiveData<String?> = Transformations.map(scoreLimit) {
        it.maxScoreValue.toString()
    }

    fun loadData() = viewModelScope.launch {
        loading.value = true
        error.value = false
        try {
            _uiScore.value = useCase.getScore()
        } catch (e: Exception) {
            error.value = true
        } finally {
            loading.value = false
        }
    }
}
