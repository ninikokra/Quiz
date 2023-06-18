package com.space.quiz.presentation.model.mapper.subject

import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.presentation.model.SubjectUIModel
import com.space.quiz.utils.UIMapper

class SubjectDomainUiMapper (
) : UIMapper<SubjectDomainModel, SubjectUIModel> {
    override fun invoke(model: SubjectDomainModel): SubjectUIModel =
        SubjectUIModel(
            id = model.id,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
            quizDescription = model.quizDescription,
            quizTitle = model.quizTitle
        )
}