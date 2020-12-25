package com.andromesh.movieslistassignment.movies.data

import com.google.gson.annotations.SerializedName

data class ProductionCompanies(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Number,

    @field:SerializedName("logo_path")
    val logo_path: String,

    @field:SerializedName("origin_country")
    val origin_country: String
)