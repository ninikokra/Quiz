package com.space.quiz.data.local.mapper

import com.space.quiz.data.local.entity.UserEntity
import com.space.quiz.domain.model.DomainUserModel
import com.space.quiz.utils.ModelMapper

class UserDomainToEntityMapper :ModelMapper<DomainUserModel,UserEntity>{
    override fun invoke(model: DomainUserModel): UserEntity =
        UserEntity(
            id = model.id,
            userName = model.userName
        )
}