package com.githubexamples.avik.fightclub.di.modules

import com.githubexamples.avik.fightclub.data.api.ApiService
import com.githubexamples.avik.fightclub.data.mappers.MovieCastApiEntityMapper
import com.githubexamples.avik.fightclub.data.mappers.MovieDetailsApiEntityMapper
import com.githubexamples.avik.fightclub.data.mappers.MovieListingApiEntityMapper
import com.githubexamples.avik.fightclub.data.repository.*
import com.githubexamples.avik.fightclub.di.scopes.MainScope
import com.githubexamples.avik.fightclub.di.scopes.MovieDetailScope
import com.githubexamples.avik.fightclub.domain.GetSimilarMovieApiSource
import com.githubexamples.avik.fightclub.domain.MovieDetailsRepository
import com.githubexamples.avik.fightclub.domain.MovieListingRepository
import com.githubexamples.avik.fightclub.domain.MovieSearchCacheRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {


    @Provides
    @Singleton
    fun provideRemoteSource(apiService: ApiService) = GetMovieListFromApiImpl(apiService)

    @Provides
    fun provideListItemEntityMapper() = MovieListingApiEntityMapper()

    @Provides
    fun provideMovieListingRepository(
        apiSource: GetMovieListFromApiImpl,
        mapper: MovieListingApiEntityMapper
    ): MovieListingRepository = MovieListingRepositoryImpl(apiSource, mapper)


    @Provides
    @Singleton
    fun provideRemoteMovieDetailsSource(apiService: ApiService) = GetMovieDetailsApiImpl(apiService)

    @Provides
    fun provideMovieDetailItemEntityMapper() = MovieDetailsApiEntityMapper()

    @Provides
    @Singleton
    fun provideMoviesCastApiSource(apiService: ApiService) = GetMovieCastFromApiImpl(apiService)

    @Provides
    fun provideMovieCastItemEntityMapper() = MovieCastApiEntityMapper()

    @Provides
    @Singleton
    fun provideSimilarMovieApiSource(apiService: ApiService) = GetSimilarMovieResponseImpl(apiService)

    @Provides
    @Singleton
    fun provideMoviesCacheRepository():MovieSearchCacheRepository = MovieSearchCacheRepositoryImpl()


    @Provides
    fun provideMovieDetailsRepository(
        movieDetailApiSource: GetMovieDetailsApiImpl,
        movieDetailMapper: MovieDetailsApiEntityMapper,
        movieCastingApiSource: GetMovieCastFromApiImpl,
        movieCastingMapper: MovieCastApiEntityMapper,
        similarMovieApiSource: GetSimilarMovieResponseImpl,
        similarMoveMapper:MovieListingApiEntityMapper
    ): MovieDetailsRepository = MovieDetailsRepositoryImpl(
        movieDetailApiSource,
        movieDetailMapper,
        movieCastingApiSource,
        movieCastingMapper,
        similarMovieApiSource,
        similarMoveMapper
    )


}
