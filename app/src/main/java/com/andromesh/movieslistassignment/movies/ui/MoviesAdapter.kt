package com.andromesh.movieslistassignment.movies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andromesh.movieslistassignment.binding.ImageClickHandler
import com.andromesh.movieslistassignment.databinding.ListItemMovieBinding
import com.andromesh.movieslistassignment.movies.data.Movie

class MoviesAdapter : PagedListAdapter<Movie, MoviesAdapter.ViewHolder>(MovieDiffCallback()) {

    private lateinit var rvMovies: RecyclerView
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        movie?.let {
            holder.apply {
                bind(
                    createOnClickListener(movie),
                    movie,
                    moviesViewModel
                )
                itemView.tag = movie
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private fun createOnClickListener(movie: Movie): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                MoviesFragmentDirections.actionMoviesFragmentToMovieFragment(movie.id, movie.title)
            it.findNavController().navigate(direction)

        }
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.rvMovies = recyclerView
    }

    private fun isGridLayoutManager() = rvMovies.layoutManager is GridLayoutManager

    fun setViewModelToBinding(moviesViewModel: MoviesViewModel) {
        this.moviesViewModel = moviesViewModel
    }


    class ViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            listener: View.OnClickListener, item: Movie,
            moviesViewModel: MoviesViewModel
        ) {
            binding.apply {
                onClickListner = listener
                movie = item
                onFavroiteClickListner = ImageClickHandler()
                viewModel = moviesViewModel
                executePendingBindings()
            }
        }
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}