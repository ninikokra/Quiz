package com.space.quiz.presentation.ui_questions.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quiz.domain.model.QuestionsDomainModel
import com.space.quiz.domain.usecase.questions.GetQuestionsUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.model.QuestionsUIModel
import com.space.quiz.presentation.model.mapper.subject.QuestionsDomainUIMapper
import com.space.quiz.presentation.ui_home.ui.HomeFragmentDirections
import com.space.quiz.presentation.ui_questions.ui.QuestionsFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuestionsViewModel(
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val questionsDomainUIMapper: QuestionsDomainUIMapper
) : BaseViewModel() {

    private val _questions = MutableStateFlow<QuestionsUIModel?>(null)
    val questions: StateFlow<QuestionsUIModel?> = _questions

    private var questionList = listOf<QuestionsDomainModel>()
    private var questionIndex: Int = 0

     suspend fun fetchQuestions(subject: String) {
        questionList = getQuestionsUseCase.invoke(subject)
    }

    fun getNextQuestion(subject: String) {
        viewModelScope.launch {
            _questions.emit(questionsDomainUIMapper(questionList[questionIndex]))
            if (questionIndex < questionList.size -1){
                questionIndex+=1
            }
        }
    }

    fun navigateToHome() {
        navigate(QuestionsFragmentDirections.actionQuestionFragmentToHomeFragment())
    }
}