package io.github.livenlearnaday.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.livenlearnaday.countrylistkotlin.data.converter.DateConverter
import io.github.livenlearnaday.countrylistkotlin.data.converter.LanguageListConverter
import io.github.livenlearnaday.countrylistkotlin.data.converter.ListConverter
import io.github.livenlearnaday.countrylistkotlin.data.entity.CountryEntity

@Database(
    entities = [
        CountryEntity::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(DateConverter::class, ListConverter::class, LanguageListConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}