package com.space.quiz.utils.network.retrofit

import retrofit2.Response

inline fun <DTO> apiDataFetcher(
    apiResponse: () -> Response<DTO>,
): DTO {
    return try {
        val response = apiResponse.invoke()
        if (response.isSuccessful) {
            response.body()!!
        } else {
            throw NetworkErrorModel(errorMessage = response.message())
        }
    } catch (e: Exception) {
        throw NetworkErrorModel(errorMessage = e.message!!)
    }
}