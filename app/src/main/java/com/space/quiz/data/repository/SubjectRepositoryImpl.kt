package com.space.quiz.data.repository

import com.space.quiz.R
import com.space.quiz.data.remote.dto.SubjectDto
import com.space.quiz.data.remote.service.QuizApiService
import com.space.quiz.domain.model.QuestionsDomainModel
import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.domain.model.mapper.QuestionsDtoDomainMapper
import com.space.quiz.domain.model.mapper.SubjectDtoDomainMapper
import com.space.quiz.domain.repository.SubjectRepository
import com.space.quiz.utils.network.ResponseHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SubjectRepositoryImpl
    (
    private val quizApiService: QuizApiService,
    private val subjectDtoToDomain: SubjectDtoDomainMapper,
    private val questionsDtoToDomainMapper: QuestionsDtoDomainMapper
) : SubjectRepository {

    override val subjectDataList: MutableList<SubjectDto> = mutableListOf()

    override suspend fun getSubject(): Flow<ResponseHandler<List<SubjectDomainModel>>> {
        return flow {
            emit(ResponseHandler.Loading())
            try {
                val response = quizApiService.get()
                if (response.isSuccessful && response.body() != null) {
                    val subjectItemDto = response.body()!!
                    subjectDataList.clear()
                    subjectDataList.addAll(subjectItemDto)
                    val subjectDomainList = subjectItemDto.map {
                        subjectDtoToDomain(it)
                    }
                    emit(ResponseHandler.Success(subjectDomainList))
                } else {
                    emit(ResponseHandler.Error(R.string.service_error_text))
                }
            } catch (e: Exception) {
                emit(ResponseHandler.Error(R.string.service_error_text))
            }
        }
    }

    override suspend fun getQuestions(subject: String): List<QuestionsDomainModel> {
        return subjectDataList.find {
            it.quizTitle == subject
        }?.questions?.map {
            questionsDtoToDomainMapper(it)
        } ?: emptyList()

    }
}

