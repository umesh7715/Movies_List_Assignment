package com.andromesh.movieslistassignment.movies.data

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movie")
data class Movie(


    @NotNull
    @field:SerializedName("adult")
    val adult: Boolean,


    @NotNull
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @NotNull
    @field:SerializedName("original_language")
    val original_language: String,

    @NotNull
    @field:SerializedName("original_title")
    val original_title: String,

    @NotNull
    @field:SerializedName("overview")
    val overview: String,

    @NotNull
    @field:SerializedName("poster_path")
    var poster_path: String,

    @NotNull
    @field:SerializedName("title")
    val title: String,

    @NotNull
    @field:SerializedName("vote_count")
    val vote_count: Int,

    @Nullable
    var isFavorite: Boolean


) {
    override fun toString(): String {
        return "Movie(adult=$adult,  id='$id', original_language='$original_language', original_title='$original_title', overview='$overview', poster_path='$poster_path', title='$title', vote_count=$vote_count)"
    }

    fun getPath(): String {
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }

    init {
        poster_path = "https://image.tmdb.org/t/p/w500$poster_path"
    }

    companion object {

        fun getPath(path: String): String {
            return "https://image.tmdb.org/t/p/w500$path"
        }
    }
}



