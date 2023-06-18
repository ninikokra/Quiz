package com.space.quiz.data.local.mapper.quiz_subject

import com.space.quiz.data.local.entity.SubjectEntity
import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.utils.UIMapper

class SubjectEntityDomainMapper : UIMapper<SubjectEntity, SubjectDomainModel> {
    override fun invoke(model: SubjectEntity): SubjectDomainModel {
        return SubjectDomainModel(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
        )
    }
}