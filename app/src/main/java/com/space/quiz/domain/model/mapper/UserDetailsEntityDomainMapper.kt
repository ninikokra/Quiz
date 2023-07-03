package com.space.quiz.domain.model.mapper

import com.space.quiz.data.local.entity.UserDetailsEntity
import com.space.quiz.domain.model.UserDetailsDomainModel
import com.space.quiz.utils.UIMapper

class UserDetailsEntityDomainMapper : UIMapper<UserDetailsEntity, UserDetailsDomainModel> {
    override fun invoke(model: UserDetailsEntity): UserDetailsDomainModel {
        return UserDetailsDomainModel(
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