package com.space.quiz.di


import com.space.quiz.domain.usecase.datastore.clear.ClearDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.save.SaveDatastoreUseCase
import com.space.quiz.domain.usecase.save_user.SaveUserUseCase
import com.space.quiz.domain.usecase.subject.GetSubjectUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single { SaveUserUseCase(get()) }
    single { SaveDatastoreUseCase(get()) }
    single { ReadDatastoreUseCase(get()) }
    single { ClearDatastoreUseCase(get()) }
    single { GetSubjectUseCaseImpl(get()) }
}



