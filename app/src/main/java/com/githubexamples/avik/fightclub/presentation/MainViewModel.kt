package com.githubexamples.avik.fightclub.presentation

import androidx.lifecycle.LifecycleObserver
import com.githubexamples.avik.fightclub.base.BaseViewModel
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.domain.usecase.GetMoviesListUseCase
import com.githubexamples.avik.fightclub.presentation.entity.MovieListingViewState
import com.githubexamples.avik.fightclub.utils.NO_NETWORK
import com.githubexamples.avik.fightclub.utils.SOMETHING_WENT_WRONG
import com.githubexamples.avik.fightclub.utils.SingleLiveEvent
import com.githubexamples.avik.fightclub.utils.rx.AppSchedulerProvider
import com.githubexamples.avik.fightclub.utils.rx.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val movieListingUseCase: GetMoviesListUseCase,
    private val scheduler: SchedulerProvider
) : BaseViewModel(), LifecycleObserver {

    private var currentPageNumber = 1

    private val movieListing: SingleLiveEvent<MovieListingViewState> by lazy { SingleLiveEvent<MovieListingViewState>() }
    private val searchButton: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent<Boolean>() }

    fun observeMovieListingState() = movieListing
    fun observeSearchButton() = searchButton

    fun getListOfMovies() {
        compositeDisposable.add(movieListingUseCase.subscribeForData(currentPageNumber)
            .subscribeOn(scheduler.io())
            .doOnSubscribe { movieListing.postValue(MovieListingViewState.Loading(true)) }
            .subscribe({
                val isError = it.isEmpty()
                if (!isError) {
                    val state = MovieListingViewState.ShowList(it)
                    movieListing.postValue(state)
                } else {
                    movieListing.postValue(MovieListingViewState.Error(SOMETHING_WENT_WRONG))
                }

            }, {
                movieListing.postValue(MovieListingViewState.Error(NO_NETWORK))
            })
        )
    }

    fun showSearchButton(){
        searchButton.postValue(true)
    }

    fun hideSearchButton(){
        searchButton.postValue(false)
    }

}