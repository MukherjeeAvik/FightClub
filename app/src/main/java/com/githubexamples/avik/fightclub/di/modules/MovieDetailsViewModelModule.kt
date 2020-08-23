package com.githubexamples.avik.fightclub.di.modules

import androidx.lifecycle.ViewModel
import com.githubexamples.avik.fightclub.di.scopes.ViewModelKey
import com.githubexamples.avik.fightclub.presentation.MainViewModel
import com.githubexamples.avik.fightclub.presentation.MovieDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieDetailsViewModel): ViewModel
}