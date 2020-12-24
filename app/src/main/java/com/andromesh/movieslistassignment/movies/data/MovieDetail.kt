package com.andromesh.movieslistassignment.movies.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_detail")
data class MovieDetail(

        @field:SerializedName("Title")
        val title: String,

        @field:SerializedName("Released")
        val released: String,

        @field:SerializedName("Runtime")
        val runtime: String,

        @field:SerializedName("Genre")
        val genre: String,

        @field:SerializedName("Actors")
        val actors: String,

        @field:SerializedName("Director")
        val director: String,

        @field:SerializedName("Plot")
        val plot: String,

        @field:SerializedName("Language")
        val language: String,

        @field:SerializedName("Poster")
        val poster: String,

        @field:SerializedName("imdbRating")
        val imdbRating: String,

        @PrimaryKey
        @field:SerializedName("imdbID")
        val imdbID: String


) {

    override fun toString(): String {
        return "MovieDetail(title='$title', released='$released', runtime='$runtime', genre='$genre', actors='$actors', director='$director', plot='$plot', language='$language', poster='$poster', imdbRating='$imdbRating', imdbID='$imdbID')"
    }
}