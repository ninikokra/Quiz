package com.space.quiz.data.local.mapper.quiz_detail

import com.space.quiz.data.local.entity.UserDetailsEntity
import com.space.quiz.domain.model.UserDetailsDomainModel
import com.space.quiz.utils.UIMapper

class UserDetailsDomainEntityMapper: UIMapper<UserDetailsDomainModel,UserDetailsEntity> {
    override fun invoke(model: UserDetailsDomainModel): UserDetailsEntity {
        return UserDetailsEntity(
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