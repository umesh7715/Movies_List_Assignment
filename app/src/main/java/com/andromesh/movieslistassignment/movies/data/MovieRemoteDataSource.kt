package com.andromesh.movieslistassignment.movies.data

import com.andromesh.movieslistassignment.BuildConfig
import com.andromesh.movieslistassignment.api.BaseDataSource
import com.andromesh.movieslistassignment.api.MoviesService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val moviesService: MoviesService) :
    BaseDataSource() {

    suspend fun fetchMovies(page: Int) =
        getResult { moviesService.getMovies(page, API_KEY) }

    suspend fun searchMovies(searchText: String, page: Int) =
        getResult { moviesService.searchMovies(searchText, page, API_KEY) }

    suspend fun getMovieDetails(id: Int) =
        getResult { moviesService.getMovieDetails(id, API_KEY) }

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

}