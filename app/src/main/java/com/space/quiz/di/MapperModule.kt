package com.space.quiz.di

import com.space.quiz.data.local.mapper.UserDomainToEntityMapper
import com.space.quiz.data.local.mapper.UserEntityToDomainMapper
import com.space.quiz.presentation.model.mapper.UserUIDomainMapper
import org.koin.dsl.module

val mapperModule = module {
    single { UserUIDomainMapper() }
    single { UserEntityToDomainMapper() }
    single { UserDomainToEntityMapper() }
}