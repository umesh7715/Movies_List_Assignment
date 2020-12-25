package com.andromesh.movieslistassignment.movies.data

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movie_detail")
data class MovieDetail(

    @NotNull
    @field:SerializedName("adult")
    val adult: Boolean,

    @NotNull
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,


    @NotNull
    @field:SerializedName("original_title")
    val original_title: String,

    @Nullable
    @field:SerializedName("overview")
    val overview: String,

    @Nullable
    @field:SerializedName("poster_path")
    var poster_path: String,

    @NotNull
    @field:SerializedName("title")
    val title: String,

    @Nullable
    @field:SerializedName("tagline")
    val tagline: String,

    @NotNull
    @field:SerializedName("vote_average")
    val vote_average: Double,

    @Nullable
    var isFavorite: Boolean,

    @NotNull
    @field:SerializedName("production_companies")
    var production_companies: List<ProductionCompanies>


) {

    companion object {

        fun getPath(path: String): String {
            return "https://image.tmdb.org/t/p/w500$path"
        }
    }
}