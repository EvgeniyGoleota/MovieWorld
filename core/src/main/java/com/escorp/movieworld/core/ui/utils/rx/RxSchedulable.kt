package com.escorp.movieworld.core.ui.utils.rx

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface RxSchedulable {

    fun Completable.subscribeIoObserveMain(): Completable

    fun <T> Single<T>.subscribeIoObserveMain(): Single<T>

    fun <T> Maybe<T>.subscribeIoObserveMain(): Maybe<T>

    fun <T> Observable<T>.subscribeIoObserveMain(): Observable<T>

    fun <T> Flowable<T>.subscribeIoObserveMain(): Flowable<T>

    fun Completable.subscribeComputationObserveMain(): Completable

    fun <T> Single<T>.subscribeComputationObserveMain(): Single<T>

    fun <T> Maybe<T>.subscribeComputationObserveMain(): Maybe<T>

    fun <T> Observable<T>.subscribeComputationObserveMain(): Observable<T>

    fun <T> Flowable<T>.subscribeComputationObserveMain(): Flowable<T>

    class Delegate : RxSchedulable {

        override fun Completable.subscribeIoObserveMain(): Completable {
            return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun <T> Single<T>.subscribeIoObserveMain(): Single<T> {
            return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun <T> Maybe<T>.subscribeIoObserveMain(): Maybe<T> {
            return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun <T> Observable<T>.subscribeIoObserveMain(): Observable<T> {
            return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun <T> Flowable<T>.subscribeIoObserveMain(): Flowable<T> {
            return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun Completable.subscribeComputationObserveMain(): Completable {
            return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun <T> Single<T>.subscribeComputationObserveMain(): Single<T> {
            return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun <T> Maybe<T>.subscribeComputationObserveMain(): Maybe<T> {
            return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun <T> Observable<T>.subscribeComputationObserveMain(): Observable<T> {
            return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
        }

        override fun <T> Flowable<T>.subscribeComputationObserveMain(): Flowable<T> {
            return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}