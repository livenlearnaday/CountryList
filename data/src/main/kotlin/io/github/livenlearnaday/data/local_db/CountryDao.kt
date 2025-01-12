package io.github.livenlearnaday.data.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.github.livenlearnaday.countrylistkotlin.data.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM country_table")
    fun getAllCountries(): List<CountryEntity>

    @Query("SELECT * FROM country_table WHERE name = :name")
    fun getCountry(name: String): CountryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCountry(countryEntity: CountryEntity)

    @Query("UPDATE country_table SET isFav = 0")
    suspend fun clearAllFavCountries()

    @Query("DELETE FROM country_table")
    suspend fun clearCountryTable()

    @Query("UPDATE country_table SET isFav = :isFav WHERE name = :name")
    suspend fun updateCountryFav(isFav: Boolean, name: String)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCountries(countries: List<CountryEntity>)

    @Query("SELECT * FROM country_table")
    fun fetchCountries(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM country_table WHERE name = :name ")
    fun fetchCountry(name: String): Flow<CountryEntity>

    @Query("SELECT * FROM country_table WHERE isFav = 1")
    fun fetchCountriesFav(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM country_table where name like  :query or capital like  :query")
    fun fetchCountriesSearchResults(query: String): Flow<List<CountryEntity>>

    @Query("SELECT * FROM country_table WHERE name = :name ")
    fun fetchCountryFromDbByName(name: String): Flow<CountryEntity>

}
