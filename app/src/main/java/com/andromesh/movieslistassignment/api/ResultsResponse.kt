package com.andromesh.movieslistassignment.api

import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("page")
    val page: Int
)