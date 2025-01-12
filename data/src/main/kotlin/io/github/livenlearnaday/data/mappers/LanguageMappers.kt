package io.github.livenlearnaday.data.mappers


import io.github.livenlearnaday.countrylistkotlin.data.entity.LanguageEntity
import io.github.livenlearnaday.data.countrylist.LanguageDto
import io.github.livenlearnaday.domain.countrylist.model.LanguageModel

fun LanguageDto?.toLanguageModel(): LanguageModel {
    return this?.let {
        LanguageModel(
            name = this.name ?: "",
            nativeName = this.nativeName ?: "",
            iso6391 = this.iso6391 ?: "",
            iso6392 = this.iso6392 ?: ""
        )
    } ?: LanguageModel()
}


fun LanguageEntity.toLanguageModel(): LanguageModel {
    return LanguageModel(
        name = this.name,
        nativeName = this.nativeName,
        iso6391 = this.iso6391,
        iso6392 = this.iso6392
    )
}

fun LanguageModel.toLanguageEntity(): LanguageEntity {
    return LanguageEntity(
        name = this.name,
        nativeName = this.nativeName,
        iso6391 = this.iso6391,
        iso6392 = this.iso6392
    )
}

