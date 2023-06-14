package com.space.quiz.di

import com.space.quiz.data.local.mapper.user.UserDomainToEntityMapper
import com.space.quiz.data.local.mapper.user.UserEntityToDomainMapper
import com.space.quiz.presentation.model.mapper.user.UserUIDomainMapper
import org.koin.dsl.module

val mapperModule = module {
    single { UserUIDomainMapper() }
    single { UserEntityToDomainMapper() }
    single { UserDomainToEntityMapper() }

}