package com.space.quiz.di

import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.domain.usecase.base.BaseUseCase
import com.space.quiz.domain.usecase.save_user.SaveUserUseCaseImpl
import com.space.quiz.utils.Resource
import org.koin.dsl.module

val useCaseModule = module {
    single<BaseUseCase<UserDomainModel, Resource>> { SaveUserUseCaseImpl(get()) }
}
