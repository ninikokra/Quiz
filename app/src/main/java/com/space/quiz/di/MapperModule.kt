package com.space.quiz.di

import com.space.quiz.data.local.mapper.quiz_detail.UserDetailsDomainEntityMapper
import com.space.quiz.data.local.mapper.user.UserDomainToEntityMapper
import com.space.quiz.data.local.mapper.user.UserEntityToDomainMapper
import com.space.quiz.domain.model.mapper.QuestionsDtoToDomainMapper
import com.space.quiz.domain.model.mapper.SubjectDtoDomainMapper
import com.space.quiz.domain.model.mapper.UserDetailsEntityDomainMapper
import com.space.quiz.presentation.model.mapper.details.UserDetailsDomainUIMapper
import com.space.quiz.presentation.model.mapper.details.UserDetailsUIDomainMapper
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

    single { QuestionsDtoToDomainMapper() }
    single { QuestionsDomainUIMapper() }

    single { UserDetailsDomainEntityMapper() }
    single { UserDetailsDomainUIMapper() }
    single { UserDetailsUIDomainMapper() }
    single { UserDetailsEntityDomainMapper() }
}