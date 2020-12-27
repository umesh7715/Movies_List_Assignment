package com.andromesh.movieslistassignment.di


import com.andromesh.movieslistassignment.movies.ui.FavoriteMoviesFragment
import com.andromesh.movieslistassignment.movies.ui.MovieFragment
import com.andromesh.movieslistassignment.movies.ui.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    //Movies
    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMoviesFragment(): FavoriteMoviesFragment


}
