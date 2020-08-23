package com.githubexamples.avik.fightclub.domain.usecase

import android.util.Log
import com.githubexamples.avik.fightclub.domain.UseCase
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Observable
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor() : UseCase<List<MovieListItem>>() {

    private val wordWeightMapper = HashMap<Int, List<MovieListItem>>()
    private val firstAlphabetMapper = HashMap<Char, List<Int>>()


    override fun subscribeForData(vararg params: Any): Observable<List<MovieListItem>> {

        return Observable.create { emitter ->
            val searchTerm = params[0].toString().trim().toLowerCase()
            val results = ArrayList<MovieListItem>()
            val uniqueMovieHap = HashMap<String, MovieListItem>()
            if (searchTerm.length == 1) {
                val associatedWords = firstAlphabetMapper[searchTerm[0]]
                associatedWords?.forEach { weights ->
                    val moviesAssociatedWord = wordWeightMapper[weights]
                    moviesAssociatedWord?.forEach {
                        uniqueMovieHap[it.movieId] = it
                    }
                }

            } else {

                val wordsInTheSearchTerm = searchTerm.split(" ")
                wordsInTheSearchTerm.forEach { eachSearchWord ->
                    val modifiedSearchWord = eachSearchWord.trim().toLowerCase()
                    val hashOfWord = modifiedSearchWord.trim().hashCode()
                    val listOfMoviesRelatedToThatWord = wordWeightMapper[hashOfWord]
                    listOfMoviesRelatedToThatWord?.forEach {
                        uniqueMovieHap[it.movieId] = it
                    }

                }
            }

            uniqueMovieHap.forEach { (key, value) ->
                results.add(value)
            }

            Log.d("SEARCH", "results are " + results)
            emitter.onNext(results)
        }
    }


    fun indexList(listOfMovies: List<MovieListItem>) {
       listOfMovies.forEach {eachItem->

            val movieTitle = eachItem.movieTitle
            val words = movieTitle.split(" ")
            Log.d("SEARCH", "words are " + words)

            words.forEach{eachWord->

                val modifiedWord = eachWord.trim().toLowerCase()

                Log.d("SEARCH", "each word is " + modifiedWord)

                val hashOfWord = modifiedWord.hashCode()

                Log.d("SEARCH", "hash of word is " + hashOfWord)


                val movieListAssociatedWithThatWord = wordWeightMapper[hashOfWord]

                if (movieListAssociatedWithThatWord == null) {
                    val movieList = ArrayList<MovieListItem>()
                    movieList.add(eachItem)
                    wordWeightMapper[hashOfWord] = movieList
                    Log.d("SEARCH", "first word so adding a new list list " + eachItem.movieTitle)
                } else {
                    val newAssociatedMovieIndexList = ArrayList(movieListAssociatedWithThatWord)
                    newAssociatedMovieIndexList.add(eachItem)
                    wordWeightMapper[hashOfWord] = newAssociatedMovieIndexList
                    Log.d("SEARCH", "already present so adding to list " + eachItem.movieTitle)
                }

                val firstCharacter = modifiedWord[0]
                val indicesOfThisCharacter = firstAlphabetMapper.get(firstCharacter)
                if (indicesOfThisCharacter == null) {
                    val referencedIndexList = ArrayList<Int>()
                    referencedIndexList.add(hashOfWord)
                    firstAlphabetMapper[firstCharacter] = referencedIndexList
                    Log.d("SEARCH", "first alphabet did not exist before " + hashOfWord)
                } else {
                    val newAssociatedAlphabetIndexList = ArrayList(indicesOfThisCharacter)
                    newAssociatedAlphabetIndexList.add(hashOfWord)
                    firstAlphabetMapper[firstCharacter] = newAssociatedAlphabetIndexList
                    Log.d("SEARCH", "linking first alphabet to " + hashOfWord)
                }

            }

        }

    }

}