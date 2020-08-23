package com.githubexamples.avik.fightclub.presentation

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.githubexamples.avik.fightclub.base.BaseViewModel
import com.githubexamples.avik.fightclub.domain.usecase.GetMovieDetailsUseCase
import com.githubexamples.avik.fightclub.domain.usecase.MovieDetailsUseCaseWrapper
import com.githubexamples.avik.fightclub.presentation.entity.MovieDetailsViewState
import com.githubexamples.avik.fightclub.utils.SingleLiveEvent
import com.githubexamples.avik.fightclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val scheduler: SchedulerProvider
) : BaseViewModel(), LifecycleObserver {

    private val movieDetailsEvents = PublishSubject.create<MovieDetailsViewState>()


    fun subscribeToUIEvents() = movieDetailsEvents


    fun getMovieDetails(movieId: String) {
        compositeDisposable.add(
            movieDetailsUseCase.subscribeForData(movieId).subscribeOn(scheduler.io())
                .subscribe({
                    Log.d("--------DETAILS--", "GTTING EVENT")
                    movieDetailsEvents.onNext(MovieDetailsViewState.MovieDetailsState(it))
                }, {

                })
        )
    }
}