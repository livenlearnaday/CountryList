package io.github.livenlearnaday.data.mappers

import io.github.livenlearnaday.data.models.ErrorResponseDto
import io.github.livenlearnaday.domain.model.ErrorModel
import io.github.livenlearnaday.domain.model.ErrorResponseModel

fun ErrorResponseDto.toErrorResponseModel(): ErrorResponseModel = ErrorResponseModel(
    errors = this.errors.map { errorDto ->
        ErrorModel(
            code = errorDto.code,
            message = errorDto.message
        )
    }
)
