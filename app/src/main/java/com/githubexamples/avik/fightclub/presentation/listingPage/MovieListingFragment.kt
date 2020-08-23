package com.githubexamples.avik.fightclub.presentation.listingPage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseFragment
import com.githubexamples.avik.fightclub.base.BaseViewHolder
import com.githubexamples.avik.fightclub.base.ViewModelProviderFactory
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.presentation.MainViewModel
import com.githubexamples.avik.fightclub.presentation.adapters.MovieListingAdapter
import com.githubexamples.avik.fightclub.presentation.entity.MovieListingViewState
import com.githubexamples.avik.fightclub.utils.hide
import com.githubexamples.avik.fightclub.utils.showAsPer
import kotlinx.android.synthetic.main.movie_listing_page.*
import javax.inject.Inject

class MovieListingFragment : BaseFragment() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    private val movieListingAdapter: MovieListingAdapter by lazy { MovieListingAdapter() }


    companion object {
        const val TAG = "LIST_MOVIES_FRAGMENT"
        fun newInstance(): MovieListingFragment {

            val args = Bundle()
            val fragment =
                MovieListingFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel =
            ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel::class.java)
        mainViewModel.getListOfMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewStates()



    }

    override fun onResume() {
        super.onResume()
        mainViewModel.showSearchButton()
    }

    override fun getLayoutId() = R.layout.movie_listing_page

    override fun getFragmentTag() = TAG


    override fun getLifeCycleObserver() = mainViewModel

    override fun reloadPage() {

    }

    private fun observeViewStates() {

        mainViewModel.observeMovieListingState().observe(this, Observer { viewState ->

            when (viewState) {

                is MovieListingViewState.Loading -> loader.showAsPer(viewState.isLoading)
                is MovieListingViewState.ShowList -> {
                    setUpAdapter(viewState.movieList)
                    loader.hide()
                }
                is MovieListingViewState.Error -> {
                    notifyUserThroughMessage(viewState.message)
                    loader.hide()
                }


            }
        })
    }

    private fun setUpAdapter(list: List<MovieListItem>) {

        movieListingAdapter.addAll(list)
        movieListingAdapter.registerForCallbacks(object :
            BaseViewHolder.ItemClickedCallback<MovieListItem> {
            override fun onClicked(data: MovieListItem) {

            }


        })
        movieListing.setHasFixedSize(true)
        movieListing.adapter = movieListingAdapter


    }


}