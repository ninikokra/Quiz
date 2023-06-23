package com.space.quiz.data.local.mapper.quiz_subject

import com.space.quiz.data.remote.dto.SubjectDto
import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.utils.UIMapper

class SubjectEntityDomainMapper : UIMapper<SubjectDto, SubjectDomainModel> {
    override fun invoke(model: SubjectDto): SubjectDomainModel {
        return SubjectDomainModel(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
        )
    }
}