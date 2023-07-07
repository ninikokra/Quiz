package com.space.quiz.presentation.ui_questions.vm

import androidx.lifecycle.viewModelScope
import com.space.quiz.domain.model.QuestionsDomainModel
import com.space.quiz.domain.model.UserDetailsDomainModel
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.domain.usecase.questions.GetQuestionsUseCase
import com.space.quiz.domain.usecase.user_points.InsertUserPointUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.model.QuestionsUIModel
import com.space.quiz.presentation.model.SubjectUIModel
import com.space.quiz.presentation.model.mapper.subject.QuestionsDomainUIMapper
import com.space.quiz.presentation.ui_questions.ui.QuestionsFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
class QuestionsViewModel(
    private val getUserDataStoreUseCase: ReadDatastoreUseCase,
    private val insertUserPointUseCase: InsertUserPointUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val questionsDomainUIMapper: QuestionsDomainUIMapper,
    private val userDetailsUIDomainMapper: UserDetailsUIDomainMapper,
) : BaseViewModel() {

    private val _questions = MutableStateFlow<QuestionsUIModel?>(null)
    val questions: StateFlow<QuestionsUIModel?> = _questions

    private val _collectedScore = MutableStateFlow<Int?>(null)
    val finalScore = _collectedScore.asStateFlow()

    private var questionList = listOf<QuestionsDomainModel>()
    private var questionIndex: Int = 0
    private var correctAnswerCount = 0


    var currentUser: String? = null


    fun updatePoints(isAnswerCorrect: Boolean) {
        viewModelScope {
            if (isAnswerCorrect) correctAnswerCount++
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            val result = getUserDataStoreUseCase.invoke()
            val currentUser = result.getOrNull()
            if (currentUser != null) {
                this@QuestionsViewModel.currentUser = currentUser
            }
        }
    }

    fun getNextQuestion(subject: String) {
        viewModelScope.launch {
            _questions.emit(questionsDomainUIMapper(questionList[questionIndex]))
            if (questionIndex < questionList.size - 1) {
                questionIndex += 1
            } else {
                saveScore()
                _collectedScore.emit(correctAnswerCount)
            }
        }
    }


    suspend fun fetchQuestions(subject: String) {
        questionList = getQuestionsUseCase.invoke(subject)
    }

    fun navigateToHome() {
        navigate(QuestionsFragmentDirections.actionQuestionFragmentToHomeFragment())
    }
}*/
class QuestionsViewModel(
    private val getUserDataStoreUseCase: ReadDatastoreUseCase,
    private val insertUserPointUseCase: InsertUserPointUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val questionsDomainUIMapper: QuestionsDomainUIMapper,
) : BaseViewModel() {

    private val _questions = MutableStateFlow<QuestionsUIModel?>(null)
    val questions: StateFlow<QuestionsUIModel?> = _questions

    private val _finalScore = MutableStateFlow<Int?>(null)
    val finalScore = _finalScore.asStateFlow()

    private var questionList = listOf<QuestionsDomainModel>()
    private var questionIndex: Int = -1
    private var correctAnswerCount = 0
    lateinit var quizSubjects: SubjectUIModel


    var currentUser: String? = null

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            val result = getUserDataStoreUseCase.invoke()
            val currentUser = result.getOrNull()
            if (currentUser != null) {
                this@QuestionsViewModel.currentUser = currentUser
            }
        }
    }
    fun getNextQuestion(subject: String) {
        viewModelScope.launch {
            if (questionIndex < questionList.size - 1) {
                questionIndex++
                _questions.value = questionsDomainUIMapper(questionList[questionIndex])
            } else {
                saveScore()
                _finalScore.value = correctAnswerCount
            }
        }
    }

    fun getCurrentScore(): Int {
        return correctAnswerCount
    }

    fun isLastQuestion(): Boolean {
        return questionIndex == questionList.size - 1
    }
    fun getQuestionIndex(): Int {
        return questionIndex
    }

    fun getQuestionSize(): Int {
        return questionList.size
    }

    fun updatePoints(isAnswerCorrect: Boolean) {
        if (isAnswerCorrect) {
            correctAnswerCount++
        }
    }
    suspend fun fetchQuestions(subject: String) {
        questionList = getQuestionsUseCase.invoke(subject)
    }

    private suspend fun saveScore() {
            val details = with(quizSubjects) {
                currentUser?.let {
                    UserDetailsDomainModel(
                        id =0,
                        collectedPoints = correctAnswerCount,
                        userName = it,
                        quizTitle = quizTitle,
                        quizDescription = quizDescription,
                        quizIcon = quizIcon,
                        questionsCount = questionsCount,
                    )
                }

            }
            insertUserPointUseCase.invoke(details)

        }

    fun navigateToHome() {
        navigate(QuestionsFragmentDirections.actionQuestionFragmentToHomeFragment())
    }
}