package com.space.quiz.data.local.mapper.user

import com.space.quiz.data.local.entity.UserEntity
import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.utils.UIMapper

class UserDomainToEntityMapper : UIMapper<UserDomainModel, UserEntity> {
    override fun invoke(model: UserDomainModel): UserEntity =
        with(model) {
            UserEntity(
                id = id,
                userName = userName,
                gpa = gpa
            )
        }
}