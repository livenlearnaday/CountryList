package io.github.livenlearnaday.domain.countrylist.usecase

fun interface UpdateCountryFavUseCase {
    suspend fun execute(isFav: Boolean, countryName: String)
}
