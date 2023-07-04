package com.space.quiz.utils.network

sealed class ResponseHelper<T>(val isLoading:Boolean){
    class Success<T> (val response:T): ResponseHelper<T>(false)
    class Error<T> (val errorResponse:Int? = null , val errorResponseString: String?= null): ResponseHelper<T>(false)
    class Loading<T> (): ResponseHelper<T>(true)
}

