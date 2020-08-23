package com.githubexamples.avik.fightclub.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseActivity
import com.githubexamples.avik.fightclub.base.ViewModelProviderFactory
import com.githubexamples.avik.fightclub.domain.entitity.MovieCast
import com.githubexamples.avik.fightclub.domain.entitity.MovieDetails
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.domain.usecase.MovieDetailsUseCaseWrapper
import com.githubexamples.avik.fightclub.presentation.adapters.MovieCastAdapter
import com.githubexamples.avik.fightclub.presentation.adapters.MovieListingAdapter
import com.githubexamples.avik.fightclub.presentation.entity.MovieDetailsViewState
import com.githubexamples.avik.fightclub.utils.*
import com.githubexamples.avik.fightclub.utils.rx.SchedulerProvider
import com.google.android.material.chip.Chip
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun getLayoutId() = R.layout.activity_movie_details
    override fun getLifeCycleObserver() = movieDetailsViewModel

    override fun getParentLayForSnackBar() = null

    private val movieDetailUiSubscription = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDetailsViewModel =
            ViewModelProvider(this, providerFactory).get(MovieDetailsViewModel::class.java)
        val movieId = intent.extras?.getString(MOVIE_ID, "") ?: ""
        if (movieId.isEmpty())
            finish()
        observeViewStates()
        movieDetailsViewModel.getMovieDetails(movieId)
        backArrow.setOnClickListener {
            finish()
        }
    }

    private fun observeViewStates() {
        movieDetailUiSubscription.add(movieDetailsViewModel.subscribeToUIEvents()
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    when (it) {
                        is MovieDetailsViewState.MovieDetailsState -> {
                            when (it.data) {
                                is MovieDetailsUseCaseWrapper.MovieDetailsSuccess -> {

                                    Log.d("--------DETAILS--", "Getting movie details")
                                    showMovieDetails(it.data.movieDetails)
                                }
                                is MovieDetailsUseCaseWrapper.MovieDetailsFailed -> {
                                    loader.hide()
                                }
                                is MovieDetailsUseCaseWrapper.MovieCastSuccess -> {
                                    Log.d("--------DETAILS--", "Getting movie cast")
                                    showCasting(it.data.movieCast)
                                }
                                is MovieDetailsUseCaseWrapper.MovieCastFailed -> {
                                    hideCasting()
                                }

                                is MovieDetailsUseCaseWrapper.RecommendedMovieSuccess -> {
                                    Log.d("--------DETAILS--", "Getting movie cast")
                                    showSimilarMovies(it.data.movieCast)
                                }
                                is MovieDetailsUseCaseWrapper.RecommendedMovieFailed -> {
                                    hideSimilarMovies()
                                }
                            }

                        }
                    }
                }, {

                })
        )
    }

    private fun showMovieDetails(movieDetails: MovieDetails) {
        loader.hide()
        movieRatingLay.show()
        movieBackdrop.loadPoster(movieDetails.backdrop)
        moviePosterImg.loadPoster(movieDetails.posterPath)

        movieDetails.genres.forEach { genre ->
            val chip =
                this.layoutInflater.inflate(R.layout.genre_chips, genreChipParent, false) as Chip
            chip.text = (genre)
            genreChipParent.addView(chip)
        }
        movieTitle.text = movieDetails.title
        movieDescription.text = movieDetails.desc
        movieRating.text = movieDetails.rating

    }

    private fun showCasting(movieCast: List<MovieCast>) {
        castingCard.show()
        castingHeading.text = getString(R.string.movie_casting)
        val movieCastAdapter = MovieCastAdapter()
        movieCastAdapter.addAll(movieCast)
        castingRv.setHasFixedSize(true)
        castingRv.adapter = movieCastAdapter
    }

    private fun hideCasting() {
        castingCard.hide()

    }

    private fun showSimilarMovies(similarMovies: List<MovieListItem>) {
        relatedMovies.show()
        relatedMoviesHeading.text = getString(R.string.movies_you_like)
        val layoutManager = PeekingLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recommendedMovieAdapter = MovieListingAdapter()
        relatedMoviesRv.layoutManager = layoutManager
        recommendedMovieAdapter.addAll(similarMovies)
        relatedMoviesRv.setHasFixedSize(true)
        relatedMoviesRv.adapter = recommendedMovieAdapter
    }

    private fun hideSimilarMovies() {
        relatedMovies.hide()

    }


    override fun onDestroy() {
        super.onDestroy()
        movieDetailUiSubscription.dispose()
    }

}