package com.space.quiz.data.repository

import com.space.quiz.R
import com.space.quiz.data.remote.dto.SubjectDto
import com.space.quiz.data.remote.service.QuizApiService
import com.space.quiz.domain.model.QuestionsDomainModel
import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.domain.model.mapper.QuestionsDtoToDomainMapper
import com.space.quiz.domain.model.mapper.SubjectDtoDomainMapper
import com.space.quiz.domain.repository.SubjectRepository
import com.space.quiz.utils.network.ResponseHelper
import com.space.quiz.utils.network.retrofit.apiDataFetcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SubjectRepositoryImpl(
    private val quizApiService: QuizApiService,
    private val subjectDtoToDomain: SubjectDtoDomainMapper,
    private val questionsDtoToDomainMapper: QuestionsDtoToDomainMapper
) : SubjectRepository {

    override val subjectDataList: MutableList<SubjectDto> = mutableListOf()

    override suspend fun getSubject(): Flow<ResponseHelper<List<SubjectDomainModel>>> {
        return flow {
            emit(ResponseHelper.Loading())
            try {
                val response = apiDataFetcher { quizApiService.get() }
                subjectDataList.clear()
                subjectDataList.addAll(response)
                val subjectDomainList = response.map { subjectDtoToDomain(it) }
                emit(ResponseHelper.Success(subjectDomainList))
            } catch (e: Throwable) {
                emit(ResponseHelper.Error(R.string.service_error_text))
            }
        }
    }

    override suspend fun getQuestions(subject: String): List<QuestionsDomainModel> {
        return subjectDataList.find { it.quizTitle == subject }
            ?.questions?.map { questionsDtoToDomainMapper(it) }
            ?: emptyList()
    }
}
