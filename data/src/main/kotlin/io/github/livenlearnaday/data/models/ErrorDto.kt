package io.github.livenlearnaday.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    val code: String,
    val message: String
)
