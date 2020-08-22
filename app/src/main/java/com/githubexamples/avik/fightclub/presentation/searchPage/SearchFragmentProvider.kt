package com.githubexamples.avik.fightclub.presentation.searchPage

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentProvider {
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun provideSearchFragment(): SearchMovieFragment

}
