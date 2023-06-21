package com.space.quiz.utils

object UserValidationRegex {
    fun isValidUsername(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{7,19}$")
        return username.matches(regex) //checks if the username string matches the specified regular expression pattern
    }
}