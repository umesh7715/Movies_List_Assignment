package com.andromesh.movieslistassignment.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andromesh.movieslistassignment.MainActivity
import com.andromesh.movieslistassignment.databinding.MovieFragmentBinding
import com.andromesh.movieslistassignment.di.Injectable
import com.andromesh.movieslistassignment.di.injectViewModel
import com.andromesh.movieslistassignment.ui_utils.hide
import com.andromesh.movieslistassignment.ui_utils.show
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import com.andromesh.movieslistassignment.database.Result

class MovieFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieViewModel: MovieViewModel

    private lateinit var imdbId: String

    private lateinit var binding: MovieFragmentBinding


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        imdbId = arguments?.let { MovieFragmentArgs.fromBundle(it).imdbID }.toString()
        (activity as MainActivity).supportActionBar?.title = arguments?.let { MovieFragmentArgs.fromBundle(it).name }


        movieViewModel = injectViewModel(viewModelFactory)
        movieViewModel.imDBID = imdbId

        binding = MovieFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        movieViewModel.movie.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    binding.movieDetails = result.data

                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

}

