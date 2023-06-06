package com.space.quiz.presentation.model.mapper

import com.space.quiz.domain.model.DomainUserModel
import com.space.quiz.presentation.model.PresentationUserModel
import com.space.quiz.utils.ModelMapper

class UserPresentationToDomainMapper :ModelMapper<PresentationUserModel,DomainUserModel> {
    override fun invoke(model: PresentationUserModel): DomainUserModel =
        DomainUserModel(
            id = model.id,
            userName = model.userName
        )
}