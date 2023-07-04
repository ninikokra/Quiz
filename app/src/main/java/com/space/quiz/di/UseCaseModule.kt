package com.space.quiz.di


import com.space.quiz.domain.usecase.datastore.clear.ClearDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.get.GetUserDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.save.SaveUserDatastoreUseCase
import com.space.quiz.domain.usecase.save_user.SaveUserUseCase
import com.space.quiz.domain.usecase.subject.GetSubjectUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { SaveUserUseCase(get()) }
    single { SaveUserDatastoreUseCase(get()) }
    single { GetUserDatastoreUseCase(get()) }
    single { ClearDatastoreUseCase(get()) }
    single { GetSubjectUseCase(get()) }
}



