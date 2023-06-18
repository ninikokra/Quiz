package com.space.quiz.domain.usecase.subject

import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.utils.network.ResponseHandler
import kotlinx.coroutines.flow.Flow

interface GetSubjectUseCase {
    suspend fun execute(): Flow<ResponseHandler<List<SubjectDomainModel>>>
}