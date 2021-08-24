package com.example.myscore.data.repository.abs

import com.example.myscore.data.model.Score

interface ScoreRepositoryAbs {
    suspend fun getScore(): Score
}
