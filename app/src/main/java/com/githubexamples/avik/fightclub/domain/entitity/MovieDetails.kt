package com.githubexamples.avik.fightclub.domain.entitity

data class MovieDetails(
    val genres: List<String>,
    val title: String,
    val desc: String,
    val posterPath: String,
    val backdrop: String,
    val rating:String
)