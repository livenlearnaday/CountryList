package io.github.livenlearnaday.data.mappers

import io.github.livenlearnaday.countrylistkotlin.data.entity.CountryEntity
import io.github.livenlearnaday.data.countrylist.CountryDto
import io.github.livenlearnaday.domain.countrylist.model.CountryModel

fun CountryDto.toCountryModel(): CountryModel {
    return CountryModel(
        name = this.name,
        capital = this.capital ?: "",
        region = this.region ?: "",
        subregion = this.subregion ?: "",
        flag = this.flag ?: "",
        languages = this.languages?.let { langs -> langs.map { it.toLanguageModel() } }
            ?: emptyList(),
        callingCodes = this.callingCodes ?: emptyList(),
        isFav = this.isFav ?: false,
        note = this.note ?: ""
    )
}

fun CountryEntity.toCountryModel(): CountryModel {
    return CountryModel(
        id = this.id,
        name = this.name,
        capital = this.capital,
        region = this.region,
        subregion = this.subregion,
        flag = this.flag,
        languages = this.languageEntities.map { it.toLanguageModel() },
        callingCodes = this.callingCodes,
        isFav = this.isFav,
        note = this.note
    )
}

fun List<CountryModel>.toCountryEntities(): List<CountryEntity> {
    return this.map { country ->
        CountryEntity(
            name = country.name,
            capital = country.capital,
            region = country.region,
            subregion = country.subregion,
            flag = country.flag,
            languageEntities = country.languages.map { it.toLanguageEntity() },
            callingCodes = country.callingCodes,
            isFav = country.isFav,
            note = country.note
        )
    }
}

fun CountryModel.toCountryEntity(): CountryEntity {
    return CountryEntity(
        name = this.name,
        capital = this.capital,
        region = this.region,
        subregion = this.subregion,
        flag = this.flag,
        languageEntities = this.languages.map { it.toLanguageEntity() },
        callingCodes = this.callingCodes,
        isFav = this.isFav,
        note = this.note
    )
}

