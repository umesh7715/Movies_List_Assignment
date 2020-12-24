package com.andromesh.movieslistassignment.api

import com.andromesh.movieslistassignment.movies.data.Movie
import com.andromesh.movieslistassignment.movies.data.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET(ENDPOINT)
    suspend fun getMovie(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): Response<MovieDetail>

    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int? = null,
        @Query("api_key") apiKey: String
    ): Response<ResultsResponse<Movie>>

    companion object {
        const val ENDPOINT = "https://api.themoviedb.org/"
    }
}