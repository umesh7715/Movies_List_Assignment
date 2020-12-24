package com.andromesh.movieslistassignment.movies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andromesh.movieslistassignment.database.resultLiveData
import com.andromesh.my_portfolio.movies.data.MoviePagedDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieDao: MovieDao,
                                          private val movieRemoteDataSource: MovieRemoteDataSource
) {

    fun observerPagedMovies(connectivityAvailable: Boolean, searchText: String,
                            coroutineScope: CoroutineScope) =
            if (connectivityAvailable) observeRemotePagedSets(searchText, coroutineScope)
            else observeLocalPagedSets(searchText)

    private fun observeLocalPagedSets(searchText: String): LiveData<PagedList<Movie>> {
        val dataSourceFactory = movieDao.getPagedMovies("%hero%")

        return LivePagedListBuilder(dataSourceFactory,
            MoviePagedDataSourceFactory.pagedListConfig()
        ).build()
    }

    private fun observeRemotePagedSets(searchText: String, ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<Movie>> {
        val dataSourceFactory = MoviePagedDataSourceFactory(searchText, movieRemoteDataSource,
                movieDao, ioCoroutineScope)
        return LivePagedListBuilder(dataSourceFactory,
            MoviePagedDataSourceFactory.pagedListConfig()
        ).build()
    }

    fun observeMovie(imdbID: String) = resultLiveData(
            databaseQuery = { movieDao.getMovieById(imdbID) },
            networkCall = { movieRemoteDataSource.fetchMovie(imdbID) },
            saveCallResult = { movieDao.insertMovieDetails(it) }
    ).distinctUntilChanged()


}