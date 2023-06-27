package com.space.quiz.utils.network.retrofit

data class NetworkErrorModel(val errorMessage: String) : Throwable(errorMessage)