package com.githubexamples.avik.fightclub.domain

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T
}