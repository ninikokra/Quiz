package com.space.quiz.domain.usecase.questions

import com.space.quiz.domain.model.QuestionsDomainModel
import com.space.quiz.domain.repository.SubjectRepository
import com.space.quiz.domain.usecase.base.BaseUseCase

class GetQuestionsUseCase(private val subjectRepository: SubjectRepository) :
    BaseUseCase<String, List<QuestionsDomainModel>>() {

    override suspend fun invoke(params: String?): List<QuestionsDomainModel> {
        params?.let { subject ->
            return subjectRepository.getQuestions(subject)
        } ?: throw IllegalArgumentException("")
    }
}

