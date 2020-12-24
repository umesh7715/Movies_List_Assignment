package com.andromesh.movieslistassignment.binding

class BindingUtils {

    companion object {
        fun appendBasePath(text: String): String? {
            return "https://image.tmdb.org/t/p/w500$text"
        }
    }

}