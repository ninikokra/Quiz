package com.space.quiz.utils

import androidx.annotation.StringRes

sealed class Resource{
    data class Error(@StringRes val errorMessage : Int):Resource()
    object Success : Resource()
}
