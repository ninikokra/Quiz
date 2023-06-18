package com.space.quiz.data.remote.service

import com.space.quiz.data.remote.dto.SubjectDto
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("e7d3bc76-6574-4afd-a294-729fe9d56ed5")
    suspend fun get(): Response<List<SubjectDto>>
}