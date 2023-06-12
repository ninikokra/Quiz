package com.space.quiz.utils

import androidx.annotation.StringRes

sealed class Resource{
    data class Error(val errorMessage : Int):Resource()
    object Success : Resource()
}

