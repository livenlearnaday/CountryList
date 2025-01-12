package io.github.livenlearnaday.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    val errors: List<ErrorDto>
)