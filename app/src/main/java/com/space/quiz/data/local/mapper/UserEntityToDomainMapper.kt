package com.space.quiz.data.local.mapper

import com.space.quiz.data.local.entity.UserEntity
import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.utils.UIMapper

class UserEntityToDomainMapper : UIMapper<UserEntity,UserDomainModel>{
    override fun invoke(model: UserEntity): UserDomainModel =
        with(model){
            UserDomainModel(
                id = id,
                userName = userName
            )
        }

}