package com.example.myscore.domain.usecase.abs

import com.example.myscore.data.model.Score
import com.example.myscore.domain.uimodel.ScoreUI

interface ScoreMapperAbs {
    fun map(input: Score): ScoreUI
}