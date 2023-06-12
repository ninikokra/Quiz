package com.space.quiz.di

import com.space.quiz.presentation.model.mapper.UserUIDomainMapper
import org.koin.dsl.module

val mapperModule = module {
    single { UserUIDomainMapper() }
}