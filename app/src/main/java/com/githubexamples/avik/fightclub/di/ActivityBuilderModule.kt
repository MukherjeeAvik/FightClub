package com.githubexamples.avik.fightclub.di

import com.githubexamples.avik.fightclub.di.modules.MainModule
import com.githubexamples.avik.fightclub.di.modules.MainViewModelModule
import com.githubexamples.avik.fightclub.di.scopes.MainScope
import com.githubexamples.avik.fightclub.presentation.MainActivity
import com.githubexamples.avik.fightclub.presentation.listingPage.MovieListingFragmentProvider
import com.githubexamples.avik.fightclub.presentation.listingPage.MovieListingModule
import com.githubexamples.avik.fightclub.presentation.searchPage.SearchFragmentModule
import com.githubexamples.avik.fightclub.presentation.searchPage.SearchFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {
    @MainScope
    @ContributesAndroidInjector(
        modules = [MainModule::class,
            MainViewModelModule::class,
            SearchFragmentProvider::class,
            SearchFragmentModule::class,
            MovieListingFragmentProvider::class,
            MovieListingModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

}
