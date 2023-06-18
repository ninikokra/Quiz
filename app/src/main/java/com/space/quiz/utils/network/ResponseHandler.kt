package com.space.quiz.utils.network

sealed class ResponseHandler<T>( val isLoading:Boolean){
    class Success<T> (val response:T): ResponseHandler<T>(false)
    class Error<T> (val errorResponse:Int): ResponseHandler<T>(false)
    class Loading<T> (): ResponseHandler<T>(true)
}

