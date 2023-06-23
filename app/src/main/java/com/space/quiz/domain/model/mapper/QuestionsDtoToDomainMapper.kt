package com.space.quiz.domain.model.mapper

import com.space.quiz.data.remote.dto.QuestionDto
import com.space.quiz.domain.model.QuestionsDomainModel
import com.space.quiz.utils.UIMapper

class QuestionsDtoToDomainMapper: UIMapper<QuestionDto,QuestionsDomainModel>{
    override fun invoke(model:QuestionDto ): QuestionsDomainModel {
        return QuestionsDomainModel(
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex,
            questionTitle = model.questionTitle,
            subjectId = model.subjectId
        )
    }
}
