package com.space.quiz.di

import com.space.quiz.data.remote.service.QuizApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://run.mocky.io/v3/"
val quizModule = module {
    single { Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build() }

    single {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get())).build()
    }
    single { provideService(get()) }

}

fun provideService(retrofit: Retrofit): QuizApiService =
    retrofit.create(QuizApiService::class.java)
