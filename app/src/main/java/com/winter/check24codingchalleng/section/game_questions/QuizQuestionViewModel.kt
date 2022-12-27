package com.winter.check24codingchalleng.section.game_questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winter.check24codingchalleng.service.extension.Resource
import com.winter.check24codingchalleng.service.model.game_model.QuizResponse
import com.winter.check24codingchalleng.service.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizQuestionViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) :
    ViewModel() {
    private val quizMutableList =
        MutableStateFlow<Resource<QuizResponse>>(Resource.OnLoading())

    val quizList: StateFlow<Resource<QuizResponse>> get() = quizMutableList

    fun getQuizQuestions() {
        viewModelScope.launch {
            quizMutableList.value = Resource.OnLoading()
            quizRepository.getQuizResponse()
                .catch { e ->
                    quizMutableList.value = Resource.OnFailure(null, e.message)
                }.collect {
                    quizMutableList.value = Resource.OnSuccess(it)
                }
        }
    }
}