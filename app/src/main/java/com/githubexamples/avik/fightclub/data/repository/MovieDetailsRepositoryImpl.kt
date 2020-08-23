package com.githubexamples.avik.fightclub.data.repository

import com.githubexamples.avik.fightclub.data.entities.CastItem
import com.githubexamples.avik.fightclub.data.entities.MovieDetailsResponse
import com.githubexamples.avik.fightclub.data.entities.ResultsItem
import com.githubexamples.avik.fightclub.domain.*
import com.githubexamples.avik.fightclub.domain.entitity.MovieCast
import com.githubexamples.avik.fightclub.domain.entitity.MovieDetails
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Single
import io.reactivex.SingleTransformer
import java.security.PrivateKey

class MovieDetailsRepositoryImpl(
    private val movieDetailsRepo: GetMovieDetailsApiSource,
    private val movieDetailsEntityMapper: Mapper<MovieDetailsResponse, MovieDetails>,
    private val movieCastingRepo: GetMovieCastingApiSource,
    private val movieCastingEntityMapper: Mapper<CastItem, MovieCast>,
    private val getSimilarMovieApiSource: GetSimilarMovieApiSource,
    private val movieListEntityMapper: Mapper<ResultsItem, MovieListItem>


) : MovieDetailsRepository {
    override fun getMovieDetails(movieId: String): Single<MovieDetails> {
        return movieDetailsRepo.getMovieDetailsFromApi(movieId)
            .map { return@map movieDetailsEntityMapper.mapFrom(it) }
    }

    override fun getMovieReviews(movieId: String) {
        TODO("Not yet implemented")
    }

    override fun getGetMovieCast(movieId: String): Single<List<MovieCast>> {
        return movieCastingRepo.getMovieDetailsFromApi(movieId).map { return@map it.cast }
            .compose(transformFromMovieCastRespone())
    }

    override fun getRecommendedMovies(movieId: String): Single<List<MovieListItem>>  {
        return getSimilarMovieApiSource.getSimilarMovies(movieId).map { return@map it.results }
            .compose(transformRecommendedListResponse())

    }


    private fun transformFromMovieCastRespone(): SingleTransformer<List<CastItem?>?, List<MovieCast>> {
        return SingleTransformer { allMovies ->
            allMovies.flattenAsObservable { return@flattenAsObservable it }
                .map { eachMovie ->
                    return@map movieCastingEntityMapper.mapFrom(eachMovie)
                }.toList()

        }

    }

    private fun transformRecommendedListResponse(): SingleTransformer<List<ResultsItem?>?, List<MovieListItem>> {
        return SingleTransformer { allMovies ->
            allMovies.flattenAsObservable { return@flattenAsObservable it }
                .map { eachMovie ->
                    return@map movieListEntityMapper.mapFrom(eachMovie)
                }.toList()

        }

    }

}
