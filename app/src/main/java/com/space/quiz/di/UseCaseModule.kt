package com.space.quiz.di


import com.space.quiz.domain.usecase.datastore.clear.ClearDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.save.SaveDatastoreUseCase
import com.space.quiz.domain.usecase.gpa.GpaUseCase
import com.space.quiz.domain.usecase.questions.GetQuestionsUseCase
import com.space.quiz.domain.usecase.save_user.SaveUserUseCase
import com.space.quiz.domain.usecase.subject.GetSubjectUseCase
import com.space.quiz.domain.usecase.user_points.GetUserPointsUseCase
import com.space.quiz.domain.usecase.user_points.InsertUserPointUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { SaveUserUseCase(get()) }
    single { SaveDatastoreUseCase(get()) }
    single { ReadDatastoreUseCase(get()) }
    single { ClearDatastoreUseCase(get()) }
    single { GetSubjectUseCase(get()) }
    single { GetQuestionsUseCase(get()) }
    single { GetUserPointsUseCase(get()) }
    single { GpaUseCase(get(),get()) }
    single { InsertUserPointUseCase(get(),get()) }
}