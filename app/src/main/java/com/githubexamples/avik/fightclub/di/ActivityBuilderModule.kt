package com.githubexamples.avik.fightclub.di

import com.githubexamples.avik.fightclub.di.modules.MainModule
import com.githubexamples.avik.fightclub.di.modules.MainViewModelModule
import com.githubexamples.avik.fightclub.di.scopes.MainScope
import com.githubexamples.avik.fightclub.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {
    @MainScope
    @ContributesAndroidInjector(
        modules = [MainModule::class,
            MainViewModelModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

}
