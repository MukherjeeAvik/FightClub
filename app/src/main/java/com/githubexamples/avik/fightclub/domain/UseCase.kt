package com.githubexamples.avik.fightclub.domain

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

abstract class UseCase<T> {


    protected val stream = PublishSubject.create<T>()
    protected val compositeDisposable by lazy { CompositeDisposable() }


    abstract fun subscribeForData(vararg params: Any): Observable<T>
    fun unsubscribeFromDataSource() {
        compositeDisposable.clear()
    }



}