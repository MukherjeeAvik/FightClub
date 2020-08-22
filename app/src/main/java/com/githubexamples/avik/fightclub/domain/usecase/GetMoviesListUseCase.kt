package com.githubexamples.avik.fightclub.domain.usecase

import com.githubexamples.avik.fightclub.domain.MovieListingRepository
import com.githubexamples.avik.fightclub.domain.UseCase
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.presentation.entity.MovieListingViewState
import com.githubexamples.avik.fightclub.utils.NO_NETWORK
import com.githubexamples.avik.fightclub.utils.SOMETHING_WENT_WRONG
import com.githubexamples.avik.fightclub.utils.rx.AppSchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject


class GetMoviesListUseCase @Inject constructor(
    private val movieListingRepo: MovieListingRepository
) : UseCase<List<MovieListItem>>() {
    override fun subscribeForData(vararg params: Any): Observable<List<MovieListItem>> {
        val pageNumber = params[0].toString().toInt()
        return movieListingRepo.getListOfMovie(pageNumber = pageNumber).toObservable()
    }


}