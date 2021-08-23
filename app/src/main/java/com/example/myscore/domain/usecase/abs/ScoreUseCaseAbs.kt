package com.example.myscore.domain.usecase.abs

import com.example.myscore.domain.uimodel.ScoreUI

interface ScoreUseCaseAbs {
    suspend fun getScore(): ScoreUI
}