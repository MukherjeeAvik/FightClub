package com.githubexamples.avik.fightclub.presentation.entity

import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem

sealed class MovieListingViewState {
    data class Loading(val isLoading: Boolean) : MovieListingViewState()
    data class ShowList(val movieList: List<MovieListItem>) : MovieListingViewState()
    data class Error(val message: String) : MovieListingViewState()
}