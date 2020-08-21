package com.githubexamples.avik.fightclub.di.modules

import androidx.lifecycle.ViewModel
import com.githubexamples.avik.fightclub.di.scopes.ViewModelKey
import com.githubexamples.avik.fightclub.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
