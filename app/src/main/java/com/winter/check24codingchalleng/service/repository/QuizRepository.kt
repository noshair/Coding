package com.winter.check24codingchalleng.service.repository

import com.winter.check24codingchalleng.service.api_service.ApiService
import com.winter.check24codingchalleng.service.baseurl.ApiConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val apiService: ApiService,
) {
    fun getQuizResponse() = flow {
        emit(apiService.getQuizList(ApiConstants.API_KEY))
    }.flowOn(Dispatchers.IO)
}