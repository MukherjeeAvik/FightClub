package com.githubexamples.avik.fightclub.domain.usecase

import com.githubexamples.avik.fightclub.domain.UseCase
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Observable
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor() : UseCase<List<MovieListItem>>() {

    //hash map to link weight of each word to all the results that contain it
    private val wordWeightMapper = HashMap<Int, List<MovieListItem>>()

    //hash map to link fist alphabet of each word to the key of the above hash map
    private val firstAlphabetMapper = HashMap<Char, List<Int>>()


    override fun subscribeForData(vararg params: Any): Observable<List<MovieListItem>> {

        return Observable.create { emitter ->

            //trim the query and convert to lowercase
            val searchTerm = params[0].toString().trim().toLowerCase()

            val results = ArrayList<MovieListItem>()
            val uniqueMovieHap = HashMap<String, MovieListItem>()

            //if just an alphabet, then loop through each of its associated weights
            //the associated weights are in turn linked to movie names containig that word
            //loop through the movies and make a list of unique ones
            if (searchTerm.length == 1) {
                val associatedWords = firstAlphabetMapper[searchTerm[0]]
                associatedWords?.forEach { weights ->
                    val moviesAssociatedWord = wordWeightMapper[weights]
                    moviesAssociatedWord?.forEach {
                        uniqueMovieHap[it.movieId] = it
                    }
                }

            }
            // if 1 word is searched, look for its existing weight , if match is found loop through the associate movies and send them
            //if more than 1 word is searched - find union of all the result returned by each word's weight
            else {
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

            //unique results added to list and returned
            uniqueMovieHap.forEach { (key, value) ->
                results.add(value)
            }

            emitter.onNext(results)
        }
    }


    fun indexList(listOfMovies: List<MovieListItem>) {
        listOfMovies.forEach { eachItem ->

            //break up every word of a sentence
            val movieTitle = eachItem.movieTitle
            val words = movieTitle.split(" ")


            words.forEach { eachWord ->

                //make it lowercase and remove spaces
                val modifiedWord = eachWord.trim().toLowerCase()


                //calculate hash of every word to assign weight
                val hashOfWord = modifiedWord.hashCode()

                //check all associated values with this weight
                val movieListAssociatedWithThatWord = wordWeightMapper[hashOfWord]

                //if this word has not been associated before, make a new insertion
                if (movieListAssociatedWithThatWord == null) {
                    val movieList = ArrayList<MovieListItem>()
                    movieList.add(eachItem)
                    wordWeightMapper[hashOfWord] = movieList

                }
                //otherwise place the associated object to existing word weight
                else {
                    val newAssociatedMovieIndexList = ArrayList(movieListAssociatedWithThatWord)
                    newAssociatedMovieIndexList.add(eachItem)
                    wordWeightMapper[hashOfWord] = newAssociatedMovieIndexList
                }

                //check the first character of a word
                val firstCharacter = modifiedWord[0]

                //check if the alphabet was associated before to weights of words starting with this alphabet
                val indicesOfThisCharacter = firstAlphabetMapper[firstCharacter]

                //if not ,then associate alphabet to list of weights starting with that alphabet
                if (indicesOfThisCharacter == null) {
                    val referencedIndexList = ArrayList<Int>()
                    referencedIndexList.add(hashOfWord)
                    firstAlphabetMapper[firstCharacter] = referencedIndexList
                }
                //if the alphabet was already associated , associate the new hash to the already existing list
                else {
                    val newAssociatedAlphabetIndexList = ArrayList(indicesOfThisCharacter)
                    newAssociatedAlphabetIndexList.add(hashOfWord)
                    firstAlphabetMapper[firstCharacter] = newAssociatedAlphabetIndexList
                }

            }

        }

    }

}