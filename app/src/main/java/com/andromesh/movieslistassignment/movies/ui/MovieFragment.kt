package com.andromesh.movieslistassignment.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andromesh.movieslistassignment.MainActivity
import com.andromesh.movieslistassignment.R
import com.andromesh.movieslistassignment.database.Result
import com.andromesh.movieslistassignment.databinding.MovieFragmentBinding
import com.andromesh.movieslistassignment.di.Injectable
import com.andromesh.movieslistassignment.di.injectViewModel
import com.andromesh.movieslistassignment.movies.data.Movie
import com.andromesh.movieslistassignment.ui_utils.hide
import com.andromesh.movieslistassignment.ui_utils.show
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MovieFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieViewModel: MovieViewModel

    private lateinit var id: Number

    private lateinit var binding: MovieFragmentBinding
    private lateinit var imageView: ImageView

    override fun onResume() {
        super.onResume()
        imageView.visibility = View.VISIBLE
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        id = arguments?.let { MovieFragmentArgs.fromBundle(it).id }!!

        var applayout =
            (activity as MainActivity).findViewById(R.id.appbar) as CollapsingToolbarLayout
        applayout.title = arguments?.let { MovieFragmentArgs.fromBundle(it).name }


        imageView = (activity as MainActivity).findViewById(R.id.ivParallxedImage) as ImageView


        movieViewModel = injectViewModel(viewModelFactory)
        movieViewModel.id = id as Int

        binding = MovieFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        movieViewModel.movie.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    binding.movieDetails = result.data

                    val url = result.data?.poster_path?.let { Movie.getPath(it) }

                    Glide.with(imageView.context)
                        .load(url)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView)

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

