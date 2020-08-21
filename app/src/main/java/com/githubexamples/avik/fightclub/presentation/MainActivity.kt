package com.githubexamples.avik.fightclub.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseActivity
import com.githubexamples.avik.fightclub.base.ViewModelProviderFactory
import com.githubexamples.avik.fightclub.navigation.MainNavigator
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var mainNavigator: MainNavigator

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)


    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getLifeCycleObserver() = mainViewModel

    override fun getParentLayForSnackBar() = TODO()

}