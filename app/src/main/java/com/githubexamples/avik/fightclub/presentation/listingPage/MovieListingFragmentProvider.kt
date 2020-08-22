package com.githubexamples.avik.fightclub.presentation.listingPage

import com.githubexamples.avik.fightclub.presentation.searchPage.SearchFragmentModule
import com.githubexamples.avik.fightclub.presentation.searchPage.SearchMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieListingFragmentProvider {
    @ContributesAndroidInjector(modules = [MovieListingModule::class])
    abstract fun provideMovieListFragment(): MovieListingFragment

}