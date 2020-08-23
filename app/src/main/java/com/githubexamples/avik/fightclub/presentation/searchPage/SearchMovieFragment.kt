package com.githubexamples.avik.fightclub.presentation.searchPage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseFragment
import com.githubexamples.avik.fightclub.base.BaseNavigator
import com.githubexamples.avik.fightclub.base.BaseViewHolder
import com.githubexamples.avik.fightclub.base.ViewModelProviderFactory
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.navigation.MainNavigator
import com.githubexamples.avik.fightclub.presentation.MainViewModel
import com.githubexamples.avik.fightclub.presentation.adapters.MovieListingAdapter
import com.githubexamples.avik.fightclub.presentation.adapters.RecentlySearchedMoviesAdapter
import com.githubexamples.avik.fightclub.presentation.entity.MovieListingViewState
import com.githubexamples.avik.fightclub.utils.hide
import com.githubexamples.avik.fightclub.utils.show
import com.githubexamples.avik.fightclub.utils.showAsPer
import kotlinx.android.synthetic.main.search_page.*
import javax.inject.Inject


class SearchMovieFragment : BaseFragment() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    @Inject
    lateinit var mainNavigator: MainNavigator


    private val movieListingAdapter: MovieListingAdapter by lazy { MovieListingAdapter() }
    private val recentlyClickedAdapter: RecentlySearchedMoviesAdapter by lazy { RecentlySearchedMoviesAdapter() }


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

        observeSearchResults()
        observeSearchText()
        searchText.requestFocus()
        showKeyboard()
        mainViewModel.getListOfCachedSearchMovies()
    }

    private fun observeSearchText() {
        searchText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                mainViewModel.searchFor(s.toString())

            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {


            }

            override fun afterTextChanged(s: Editable) {


            }
        })
    }

    private fun observeSearchResults() {
        mainViewModel.observeSearchResults().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {

                is MovieListingViewState.Loading -> searchPageLoader.showAsPer(viewState.isLoading)
                is MovieListingViewState.ShowList -> {
                    setUpAdapter(viewState.movieList)
                    searchPageLoader.hide()
                    removeErrors()
                    searchResultHeading.show()
                    searchResultHeading.text = getString(R.string.reuslts_heading)
                }
                is MovieListingViewState.Error -> {
                    setUpAdapter(ArrayList())
                    notifyUserThroughMessage(viewState.message)
                    searchPageLoader.hide()
                    searchResultHeading.hide()
                }


            }
        })

        mainViewModel.observeRecentlySearchedMovies()
            .observe(viewLifecycleOwner, Observer { viewState ->
                when (viewState) {

                    is MovieListingViewState.Loading -> searchPageLoader.showAsPer(viewState.isLoading)
                    is MovieListingViewState.ShowList -> {
                        setUpRecentCache(viewState.movieList)
                        searchPageLoader.hide()
                        removeErrors()
                        recentlySearchedHeading.text = getString(R.string.recent_search_header)
                    }
                    is MovieListingViewState.Error -> {
                        setUpRecentCache(ArrayList())
                        searchPageLoader.hide()
                        recentlySearchedHeading.hide()
                    }


                }
            })

        mainViewModel.observeIfMovieSavedToCache().observe(viewLifecycleOwner, Observer { movieId ->

            mainNavigator.openMovieDetailsPage(movieId, requireContext())
        })

    }

    private fun setUpAdapter(list: List<MovieListItem>) {

        movieListingAdapter.addAll(list)
        movieListingAdapter.registerForCallbacks(object :
            BaseViewHolder.ItemClickedCallback<MovieListItem> {
            override fun onClicked(data: MovieListItem) {
                mainViewModel.putMovieToCache(data)
            }


        })
        searchMoviesRv.setHasFixedSize(true)
        searchMoviesRv.adapter = movieListingAdapter


    }

    private fun setUpRecentCache(list: List<MovieListItem>) {
        recentlyClickedAdapter.addAll(list)
        recentlyClickedAdapter.registerForCallbacks(object :
            BaseViewHolder.ItemClickedCallback<MovieListItem> {
            override fun onClicked(data: MovieListItem) {
            }


        })
        lastSearchedMovies.setHasFixedSize(true)
        lastSearchedMovies.adapter = recentlyClickedAdapter

    }

    override fun getLayoutId() = R.layout.search_page

    override fun getFragmentTag() = TAG


    override fun getLifeCycleObserver() = mainViewModel

    override fun reloadPage() {

    }


}