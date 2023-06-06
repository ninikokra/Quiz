package com.space.quiz.di

import com.space.quiz.domain.usecase.save_user.SaveUserUseCase
import com.space.quiz.domain.usecase.save_user.SaveUserUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<SaveUserUseCase> { SaveUserUseCaseImpl(get()) }
}