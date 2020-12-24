package com.andromesh.movieslistassignment.movies.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM MOVIE")
    fun getPagedMovies(): DataSource.Factory<Int, Movie>


    @Query("SELECT * FROM MOVIE WHERE title LIKE :seracherText ")
    fun getSearchedPagedMovies(seracherText: String): DataSource.Factory<Int, Movie>


    @Query("SELECT * FROM movie_detail WHERE imdbID= :id ")
    fun getMovieById(id: Int): LiveData<MovieDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetail: MovieDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(results: List<Movie>)


}