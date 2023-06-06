package com.space.quiz.domain.usecase.save_user

import com.space.quiz.domain.model.DomainUserModel

interface SaveUserUseCase {
    suspend fun saveUser(user: DomainUserModel):Boolean

}