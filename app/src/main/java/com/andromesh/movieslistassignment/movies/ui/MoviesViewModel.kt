package com.andromesh.movieslistassignment.movies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andromesh.movieslistassignment.di.CoroutineScropeSupervisor
import com.andromesh.movieslistassignment.movies.data.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject


class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository,
                                          @CoroutineScropeSupervisor private val ioCoroutineScope: CoroutineScope) : ViewModel() {


    var connectivityAvailable: Boolean = false


    var searchFilterText = MutableLiveData("hero")

    val moviesList = Transformations.switchMap(searchFilterText) { input ->
        movieRepository.observerPagedMovies(
                connectivityAvailable, input, ioCoroutineScope)
    }


    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }

}
