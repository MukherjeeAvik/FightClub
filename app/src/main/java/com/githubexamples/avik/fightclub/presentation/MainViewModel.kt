package com.githubexamples.avik.fightclub.presentation

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.githubexamples.avik.fightclub.base.BaseViewModel
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.domain.usecase.CacheMovieUseCase
import com.githubexamples.avik.fightclub.domain.usecase.GetMoviesListUseCase
import com.githubexamples.avik.fightclub.domain.usecase.SearchMovieUseCase
import com.githubexamples.avik.fightclub.presentation.entity.MovieListingViewState
import com.githubexamples.avik.fightclub.utils.NO_NETWORK
import com.githubexamples.avik.fightclub.utils.NO_RESULTS_FOUND
import com.githubexamples.avik.fightclub.utils.SOMETHING_WENT_WRONG
import com.githubexamples.avik.fightclub.utils.SingleLiveEvent
import com.githubexamples.avik.fightclub.utils.rx.AppSchedulerProvider
import com.githubexamples.avik.fightclub.utils.rx.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val movieListingUseCase: GetMoviesListUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val cacheSearchedMovies: CacheMovieUseCase,
    private val scheduler: SchedulerProvider
) : BaseViewModel(), LifecycleObserver {

    private var currentPageNumber = 1

    private val movieListing: MutableLiveData<MovieListingViewState> by lazy { MutableLiveData<MovieListingViewState>() }
    private val searchButton: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent<Boolean>() }
    private val searchResult: SingleLiveEvent<MovieListingViewState> by lazy { SingleLiveEvent<MovieListingViewState>() }
    private val recentMovies: SingleLiveEvent<MovieListingViewState> by lazy { SingleLiveEvent<MovieListingViewState>() }
    private val movieCachedSuccessfully: SingleLiveEvent<String> by lazy { SingleLiveEvent<String>() }

    fun observeMovieListingState() = movieListing
    fun observeSearchButton() = searchButton
    fun observeSearchResults() = searchResult
    fun observeRecentlySearchedMovies() = recentMovies
    fun observeIfMovieSavedToCache() = movieCachedSuccessfully

    fun getListOfMovies() {
        compositeDisposable.add(movieListingUseCase.subscribeForData(currentPageNumber)
            .subscribeOn(scheduler.computation())
            .doOnSubscribe { movieListing.postValue(MovieListingViewState.Loading(true)) }
            .subscribe({
                val isError = it.isEmpty()
                if (!isError) {
                    searchMovieUseCase.indexList(it)
                    val state = MovieListingViewState.ShowList(it)
                    movieListing.postValue(state)
                } else {
                    movieListing.postValue(MovieListingViewState.Error(SOMETHING_WENT_WRONG))
                }

            }, {
                it.printStackTrace()
                movieListing.postValue(MovieListingViewState.Error(NO_NETWORK))
            })
        )
    }

    fun showSearchButton() {
        searchButton.postValue(true)
    }

    fun hideSearchButton() {
        searchButton.postValue(false)
    }

    fun searchFor(query: String) {
        compositeDisposable.add(
            searchMovieUseCase.subscribeForData(query).subscribeOn(scheduler.io()).subscribe({
                val isError = it.isEmpty()
                if (!isError) {
                    val state = MovieListingViewState.ShowList(it)
                    searchResult.postValue(state)
                } else {
                    searchResult.postValue(MovieListingViewState.Error(NO_RESULTS_FOUND))
                }

            }, {
                searchResult.postValue(MovieListingViewState.Error(SOMETHING_WENT_WRONG))
            })
        )
    }

    fun getListOfCachedSearchMovies() {
        compositeDisposable.add(
            cacheSearchedMovies.subscribeForData().subscribeOn(scheduler.computation()).subscribe({
                val isError = it.isEmpty()
                if (!isError) {
                    val state = MovieListingViewState.ShowList(it)
                    recentMovies.postValue(state)
                } else {
                    recentMovies.postValue(MovieListingViewState.Error(NO_RESULTS_FOUND))
                }

            }, {
                recentMovies.postValue(MovieListingViewState.Error(SOMETHING_WENT_WRONG))
            })
        )
    }

    fun putMovieToCache(movie: MovieListItem) {
        compositeDisposable.add(
            cacheSearchedMovies.storeToCache(movie)
                .subscribeOn(scheduler.io()).subscribe({
                    movieCachedSuccessfully.postValue(movie.movieId)
                },
                    {
                        movieCachedSuccessfully.postValue(movie.movieId)
                    })
        )

    }
}