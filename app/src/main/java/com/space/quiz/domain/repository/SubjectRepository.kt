package com.space.quiz.domain.repository

import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.utils.network.ResponseHandler
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
        suspend fun getSubject(): Flow<ResponseHandler<List<SubjectDomainModel>>>
}