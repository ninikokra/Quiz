package com.space.quiz.di

import com.space.quiz.data.local.mapper.UserDomainToEntityMapper
import com.space.quiz.data.local.mapper.UserEntityToDomainMapper
import com.space.quiz.data.repository.UserRepositoryImpl
import com.space.quiz.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get(), UserEntityToDomainMapper(), UserDomainToEntityMapper())
    }
}