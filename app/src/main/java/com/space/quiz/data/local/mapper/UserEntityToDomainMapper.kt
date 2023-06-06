package com.space.quiz.data.local.mapper

import com.space.quiz.data.local.entity.UserEntity
import com.space.quiz.domain.model.DomainUserModel
import com.space.quiz.utils.ModelMapper

class UserEntityToDomainMapper : ModelMapper<UserEntity,DomainUserModel>{
    override fun invoke(model: UserEntity): DomainUserModel =
        DomainUserModel(
            id = model.id,
            userName = model.userName
        )
}