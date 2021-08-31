package com.example.myscore.data.repository

import com.example.myscore.data.api.ScoreApi
import com.example.myscore.data.repository.abs.ScoreRepositoryAbs

class ScoreRepository(private val api: ScoreApi) : ScoreRepositoryAbs {
    override suspend fun getScore() = api.getScore()
}
