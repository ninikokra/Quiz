package com.space.quiz.presentation.model.mapper.user

import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.presentation.model.UserUIModel
import com.space.quiz.utils.UIMapper

class UserUIDomainMapper :UIMapper<UserUIModel,UserDomainModel> {
    override fun invoke(model: UserUIModel): UserDomainModel =
        UserDomainModel(
            id = model.id,
            userName = model.userName
        )
}