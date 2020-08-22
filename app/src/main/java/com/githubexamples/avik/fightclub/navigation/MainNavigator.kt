package com.githubexamples.avik.fightclub.navigation

import androidx.fragment.app.FragmentManager
import com.githubexamples.avik.fightclub.base.BaseNavigator
import com.githubexamples.avik.fightclub.presentation.listingPage.MovieListingFragment
import com.githubexamples.avik.fightclub.presentation.searchPage.SearchMovieFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class MainNavigator @Inject constructor(fragmentManager: FragmentManager) :
    BaseNavigator(fragmentManager) {

    fun openListingPage(container: Int, addToBackStack: Boolean) {

        var fragment = findFragment(MovieListingFragment.TAG)
        if (fragment == null) {
            fragment = MovieListingFragment.newInstance()
        }

        showFragmentWithAnimation(
            fragment,
            MovieListingFragment.TAG,
            addToBackStack,
            container
        )

    }

    fun openSearchPage(container: Int, addToBackStack: Boolean) {

        var fragment = findFragment(SearchMovieFragment.TAG)
        if (fragment == null) {
            fragment = SearchMovieFragment.newInstance()
        }

        showFragmentWithAnimation(
            fragment,
            SearchMovieFragment.TAG,
            addToBackStack,
            container
        )

    }

}