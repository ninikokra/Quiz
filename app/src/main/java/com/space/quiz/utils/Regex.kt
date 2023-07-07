package com.space.quiz.utils

object UserValidationRegex {
    fun isValidUsername(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9ა-ჰ][a-zA-Z0-9ა-ჰ_]{7,19}$")
        return username.matches(regex)
    }
}
