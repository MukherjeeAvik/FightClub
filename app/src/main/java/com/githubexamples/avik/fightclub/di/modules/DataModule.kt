package com.githubexamples.avik.fightclub.di.modules

import com.githubexamples.avik.fightclub.data.api.ApiService
import com.githubexamples.avik.fightclub.data.mappers.MovieListingApiEntityMapper
import com.githubexamples.avik.fightclub.data.repository.GetMovieListFromApiImpl
import com.githubexamples.avik.fightclub.data.repository.MovieListingRepositoryImpl
import com.githubexamples.avik.fightclub.domain.MovieListingRepository
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
    ):MovieListingRepository = MovieListingRepositoryImpl(apiSource, mapper)

}
