package com.omersakalli.marvelcomics.data

data class Result<out T>(
    val variable: T? = null,
    val isSuccessful: Boolean,
    val errorMessage: String? = null
)