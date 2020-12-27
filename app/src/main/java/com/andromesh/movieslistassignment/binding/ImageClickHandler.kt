package com.andromesh.movieslistassignment.binding

import android.view.View
import android.widget.ImageButton
import com.andromesh.movieslistassignment.R
import com.andromesh.movieslistassignment.movies.data.Movie
import com.andromesh.movieslistassignment.movies.ui.MoviesViewModel

class ImageClickHandler {

    fun onImageButtonClicked(
        imageButton: View,
        movie: Movie,
        movieViewModel: MoviesViewModel?
    ) {


        movie.isFavorite = !movie.isFavorite
        movieViewModel?.updateMovie(movie)

        /*if (movie.isFavorite) {
            (imageButton as ImageButton).setImageResource(R.drawable.ic_favorite_filled)
        } else {
            (imageButton as ImageButton).setImageResource(R.drawable.ic_favorite_hollow)
        }*/
    }
}