package com.escorp.movieworld.test_unit_base

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.observers.TestObserver

fun <T> LiveData<T>.test(): TestObserver<T> {
    return Observable.create<T> { observeForever(it::onNext) }.test()
}