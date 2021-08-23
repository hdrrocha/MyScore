package com.example.myscore.data.api

import com.example.myscore.data.model.Score
import retrofit2.http.GET

interface ScoreApi {
    @GET("endpoint.json")
    suspend fun getScore(): Score
}