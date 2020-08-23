package com.githubexamples.avik.fightclub.presentation.entity

import com.githubexamples.avik.fightclub.domain.usecase.MovieDetailsUseCaseWrapper

sealed class MovieDetailsViewState {

    data class MovieDetailsState(val data: MovieDetailsUseCaseWrapper) : MovieDetailsViewState()
    data class Loading(val loading: Boolean) : MovieDetailsViewState()

}