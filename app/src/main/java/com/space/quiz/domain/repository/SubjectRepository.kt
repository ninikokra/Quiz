package com.space.quiz.domain.repository

import com.space.quiz.data.remote.dto.SubjectDto
import com.space.quiz.domain.model.QuestionsDomainModel
import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.utils.network.ResponseHandler
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
        suspend fun getSubject(): Flow<ResponseHandler<List<SubjectDomainModel>>>
        suspend fun getQuestions(subject: String): List<QuestionsDomainModel>
        val subjectDataList: MutableList<SubjectDto>
}