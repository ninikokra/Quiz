package com.space.quiz.presentation.model.mapper.details

import com.space.quiz.domain.model.UserDetailsDomainModel
import com.space.quiz.presentation.model.mapper.UserDetailsUIModel
import com.space.quiz.utils.UIMapper

class UserDetailsDomainUIMapper : UIMapper<UserDetailsDomainModel,UserDetailsUIModel>{
    override fun invoke(model: UserDetailsDomainModel): UserDetailsUIModel {
        return UserDetailsUIModel(
            id = model.id,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            quizTitle = model.quizTitle,
            collectedPoints = model.collectedPoints,
            userName = model.userName,
            questionsCount = model.questionsCount
        )
    }
}