package com.githubexamples.avik.fightclub.presentation.searchPage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseFragment
import com.githubexamples.avik.fightclub.base.ViewModelProviderFactory
import com.githubexamples.avik.fightclub.presentation.MainViewModel
import com.githubexamples.avik.fightclub.presentation.adapters.MovieListingAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_page.*
import javax.inject.Inject


class SearchMovieFragment: BaseFragment() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory




    private val movieListingAdapter: MovieListingAdapter by lazy { MovieListingAdapter() }


    companion object {
        const val TAG = "SEARCH_MOVIE_FRAGMENT"
        fun newInstance(): SearchMovieFragment {

            val args = Bundle()
            val fragment =
                SearchMovieFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onResume() {
        super.onResume()
        mainViewModel.hideSearchButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel =
            ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId() = R.layout.search_page

    override fun getFragmentTag() = TAG



    override fun getLifeCycleObserver() = mainViewModel

    override fun reloadPage() {

    }


}