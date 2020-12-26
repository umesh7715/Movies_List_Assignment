package com.andromesh.movieslistassignment.movies.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import retrofit2.Response

@Dao
interface MovieDao {

    @Query("SELECT * FROM MOVIE")
    fun getPagedMovies(): DataSource.Factory<Int, Movie>


    @Query("SELECT * FROM MOVIE WHERE title LIKE :seracherText ")
    fun getSearchedPagedMovies(seracherText: String): DataSource.Factory<Int, Movie>


    @Query("SELECT * FROM movie_detail WHERE id= :id ")
    fun getMovieById(id: Int): LiveData<MovieDetail>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieDetails(movieDetail: MovieDetail)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(results: List<Movie>)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("SELECT * FROM MOVIE WHERE isFavorite = 1 ")
    suspend fun getFavoriteMovies(): Response<List<Movie>>


}