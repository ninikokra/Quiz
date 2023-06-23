package com.space.quiz.domain.usecase.subject

import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.domain.repository.SubjectRepository
import com.space.quiz.domain.usecase.base.BaseUseCase
import com.space.quiz.utils.network.ResponseHandler
import kotlinx.coroutines.flow.Flow


class GetSubjectUseCase(private val subjectRepository: SubjectRepository) : BaseUseCase<Unit,Flow<ResponseHandler<List<SubjectDomainModel>>>>() {
    override suspend fun invoke(params: Unit?): Flow<ResponseHandler<List<SubjectDomainModel>>> {
        return subjectRepository.getSubject()
    }
}
