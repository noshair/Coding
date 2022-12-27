package com.winter.check24codingchalleng.service.api_service

import com.winter.check24codingchalleng.service.model.game_model.QuizResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/api/v1/questions?")
    suspend fun getQuizList(@Query("apiKey")apiKey:String): QuizResponse
}