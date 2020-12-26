package com.andromesh.movieslistassignment.movies.ui

import androidx.lifecycle.ViewModel
import com.andromesh.movieslistassignment.di.CoroutineScropeIO
import com.andromesh.movieslistassignment.movies.data.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates


class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {

    var id by Delegates.notNull<Int>()

    val movie by lazy { repository.observeMovieDetals(id) }



    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}