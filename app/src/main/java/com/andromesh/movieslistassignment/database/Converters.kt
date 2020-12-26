package com.andromesh.movieslistassignment.database

import androidx.room.TypeConverter
import com.andromesh.movieslistassignment.movies.data.Genre
import com.andromesh.movieslistassignment.movies.data.ProductionCompanies
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

    @TypeConverter
    fun fromProductionCompaniesList(production: List<ProductionCompanies?>?): String? {
        if (production == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<ProductionCompanies?>?>() {}.type
        return gson.toJson(production, type)
    }

    @TypeConverter
    fun toProductionCompaniesList(cproductionString: String?): List<ProductionCompanies>? {
        if (cproductionString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<ProductionCompanies?>?>() {}.type
        return gson.fromJson<List<ProductionCompanies>>(cproductionString, type)
    }

    @TypeConverter
    fun fromGenresList(genre: List<Genre?>?): String? {
        if (genre == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Genre?>?>() {}.type
        return gson.toJson(genre, type)
    }

    @TypeConverter
    fun toGenresList(genreString: String?): List<Genre>? {
        if (genreString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Genre?>?>() {}.type
        return gson.fromJson<List<Genre>>(genreString, type)
    }
}