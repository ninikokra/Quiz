package com.space.quiz

import android.app.Application
import com.space.quiz.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class QuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QuizApp)
            modules(
                dbModule, repositoryModule,useCaseModule,viewModelModule, mapperModule,
                userDataStore, quizModule
            )
        }
    }
}