package com.githubexamples.avik.fightclub.data.api.utils

class NoInternetAvailableException : Exception() {
    
    override val message: String?
        get() = "No internet available"

}
