package com.space.quiz.di

import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.domain.usecase.base.BaseUseCase
import com.space.quiz.domain.usecase.datastore.clear.ClearUserUseCase
import com.space.quiz.domain.usecase.datastore.clear.ClearUserUseCaseImpl
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCaseImpl
import com.space.quiz.domain.usecase.datastore.save.SaveDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.save.SaveDatastoreUseCaseImpl
import com.space.quiz.domain.usecase.save_user.SaveUserUseCaseImpl
import com.space.quiz.domain.usecase.subject.GetSubjectUseCase
import com.space.quiz.domain.usecase.subject.GetSubjectUseCaseImpl
import com.space.quiz.utils.Resource
import org.koin.dsl.module

val useCaseModule = module {
    single<BaseUseCase<UserDomainModel, Resource>> { SaveUserUseCaseImpl(get()) }
  /*  single<BaseUseCase<String, Unit>> { SaveDatastoreUseCase(get()) }
    single<BaseUseCase<Unit,Result<String>>> { ReadDatastoreUseCase(get()) }
    single<BaseUseCase<Unit, Result<Unit>>> { ClearDatastoreUseCase(get()) }
*/
    single<SaveDatastoreUseCase> { SaveDatastoreUseCaseImpl(get()) }
    single<ReadDatastoreUseCase> { ReadDatastoreUseCaseImpl(get()) }
    single<ClearUserUseCase> { ClearUserUseCaseImpl(get()) }

    single<GetSubjectUseCase> { GetSubjectUseCaseImpl(get()) }


}



