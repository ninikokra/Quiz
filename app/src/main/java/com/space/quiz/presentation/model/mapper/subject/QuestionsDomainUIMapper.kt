package com.space.quiz.presentation.model.mapper.subject

import com.space.quiz.domain.model.QuestionsDomainModel
import com.space.quiz.presentation.model.QuestionsUIModel
import com.space.quiz.utils.UIMapper

class QuestionsDomainUIMapper(
) : UIMapper<QuestionsDomainModel, QuestionsUIModel> {
    override fun invoke(model: QuestionsDomainModel): QuestionsUIModel =
        QuestionsUIModel(
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex,
            questionTitle = model.questionTitle,
            subjectId = model.subjectId
        )

}