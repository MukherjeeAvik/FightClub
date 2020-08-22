package com.githubexamples.avik.fightclub.presentation

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseActivity
import com.githubexamples.avik.fightclub.base.BaseViewHolder
import com.githubexamples.avik.fightclub.base.ViewModelProviderFactory
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.navigation.MainNavigator
import com.githubexamples.avik.fightclub.presentation.adapters.MovieListingAdapter
import com.githubexamples.avik.fightclub.presentation.entity.MovieListingViewState
import com.githubexamples.avik.fightclub.utils.hide
import com.githubexamples.avik.fightclub.utils.showAsPer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*
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
        mainNavigator.openListingPage(R.id.fragmentContainer, false)
        searchMovieBtn.setOnClickListener {
            mainNavigator.openSearchPage(R.id.fragmentContainer, true)
        }
        observeUiForChanges()

    }

    private fun observeUiForChanges() {
        mainViewModel.observeSearchButton().observe(this, Observer { shouldShow ->
            if (shouldShow)
                searchMovieBtn.show()
            else
                searchMovieBtn.hide()
        })
    }


    override fun getLayoutId() = R.layout.activity_main

    override fun getLifeCycleObserver() = mainViewModel

    override fun getParentLayForSnackBar() = parentLayout


}