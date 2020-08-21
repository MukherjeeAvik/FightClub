package com.githubexamples.avik.fightclub.di.modules

import android.app.Application
import android.content.Context
import com.githubexamples.avik.fightclub.utils.rx.AppSchedulerProvider
import com.githubexamples.avik.fightclub.utils.rx.SchedulerProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(context: Application): Context = context


    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()


    @Provides
    @Singleton
    fun provideGson() = Gson()


}



