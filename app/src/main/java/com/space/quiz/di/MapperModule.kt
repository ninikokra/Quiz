package com.space.quiz.di

import com.space.quiz.data.local.mapper.user.UserDomainToEntityMapper
import com.space.quiz.data.local.mapper.user.UserEntityToDomainMapper
import com.space.quiz.domain.model.mapper.QuestionsDtoDomainMapper
import com.space.quiz.domain.model.mapper.SubjectDtoDomainMapper
import com.space.quiz.presentation.model.mapper.subject.QuestionsDomainUIMapper
import com.space.quiz.presentation.model.mapper.subject.SubjectDomainUiMapper
import com.space.quiz.presentation.model.mapper.user.UserUIDomainMapper
import org.koin.dsl.module

val mapperModule = module {
    single { UserUIDomainMapper() }
    single { UserEntityToDomainMapper() }
    single { UserDomainToEntityMapper() }
    single { SubjectDtoDomainMapper() }
    single { SubjectDomainUiMapper() }
    single { QuestionsDtoDomainMapper() }
    single { QuestionsDomainUIMapper() }
}