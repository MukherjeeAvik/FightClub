package com.githubexamples.avik.fightclub.domain.usecase

import com.githubexamples.avik.fightclub.domain.MovieDetailsRepository
import com.githubexamples.avik.fightclub.domain.UseCase
import com.githubexamples.avik.fightclub.domain.entitity.MovieCast
import com.githubexamples.avik.fightclub.domain.entitity.MovieDetails
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.utils.rx.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val scheduler: SchedulerProvider
) :
    UseCase<MovieDetailsUseCaseWrapper>() {
    override fun subscribeForData(vararg params: Any): Observable<MovieDetailsUseCaseWrapper> {
        val movieId = params[0].toString()
        compositeDisposable.add(
            movieDetailsRepository.getMovieDetails(movieId).subscribeOn(scheduler.io())
                .subscribe({
                    stream.onNext(MovieDetailsUseCaseWrapper.MovieDetailsSuccess(it))
                }, {
                    stream.onNext(MovieDetailsUseCaseWrapper.MovieDetailsFailed)
                })
        )

        compositeDisposable.add(
            movieDetailsRepository.getGetMovieCast(movieId).subscribeOn(scheduler.io())
                .subscribe({
                    stream.onNext(MovieDetailsUseCaseWrapper.MovieCastSuccess(it))
                }, {
                    stream.onNext(MovieDetailsUseCaseWrapper.MovieCastFailed)
                })
        )

        compositeDisposable.add(
            movieDetailsRepository.getRecommendedMovies(movieId).subscribeOn(scheduler.io())
                .subscribe({
                    stream.onNext(MovieDetailsUseCaseWrapper.RecommendedMovieSuccess(it))
                }, {
                    stream.onNext(MovieDetailsUseCaseWrapper.RecommendedMovieFailed)
                })
        )

        return stream
    }


}

sealed class MovieDetailsUseCaseWrapper {
    data class MovieDetailsSuccess(val movieDetails: MovieDetails) : MovieDetailsUseCaseWrapper()
    object MovieDetailsFailed : MovieDetailsUseCaseWrapper()

    data class MovieCastSuccess(val movieCast: List<MovieCast>) : MovieDetailsUseCaseWrapper()
    object MovieCastFailed : MovieDetailsUseCaseWrapper()


    data class RecommendedMovieSuccess(val movieCast: List<MovieListItem>) : MovieDetailsUseCaseWrapper()
    object RecommendedMovieFailed : MovieDetailsUseCaseWrapper()
}