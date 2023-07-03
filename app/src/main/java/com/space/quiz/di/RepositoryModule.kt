package com.space.quiz.di

import com.space.quiz.data.repository.SubjectRepositoryImpl
import com.space.quiz.data.repository.UserDetailsRepositoryImpl
import com.space.quiz.data.repository.UserRepositoryImpl
import com.space.quiz.domain.model.mapper.SubjectDtoDomainMapper
import com.space.quiz.domain.repository.SubjectRepository
import com.space.quiz.domain.repository.UserDetailsRepository
import com.space.quiz.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }
    single<SubjectRepository> { SubjectRepositoryImpl(get(), get(),get()) }
    single<UserDetailsRepository> { UserDetailsRepositoryImpl(get(), get(),get()) }
}