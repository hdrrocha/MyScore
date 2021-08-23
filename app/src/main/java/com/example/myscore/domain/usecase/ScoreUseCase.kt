package com.example.myscore.domain.usecase

import com.example.myscore.data.repository.abs.ScoreRepositoryAbs
import com.example.myscore.domain.mapper.abs.ScoreMapperAbs
import com.example.myscore.domain.usecase.abs.ScoreUseCaseAbs

class ScoreUseCase(
    private val repository: ScoreRepositoryAbs,
    private val mapper: ScoreMapperAbs
) : ScoreUseCaseAbs {
    override suspend fun getScore() = mapper.map(
        repository.getScore()
    )
}