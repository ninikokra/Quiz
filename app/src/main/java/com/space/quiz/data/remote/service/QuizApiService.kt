package com.space.quiz.data.remote.service

import com.space.quiz.data.remote.dto.SubjectDto
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("8ade4e0b-bee1-4eae-a98b-47edeea68324")
    suspend fun get(): Response<List<SubjectDto>>
}