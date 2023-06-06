package com.space.quiz.utils

fun isUserNameCorrect(username: String): Boolean {
    val regex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{7,19}$")
    return username.matches(regex)
}