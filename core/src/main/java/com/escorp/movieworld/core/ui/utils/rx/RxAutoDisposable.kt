package com.escorp.movieworld.core.ui.utils.rx

import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

interface RxAutoDisposable {

    fun Completable.autoDisposableSubscribe(
        onComplete: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    )

    fun <T> Single<T>.autoDisposableSubscribe(
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    )

    fun <T> Maybe<T>.autoDisposableSubscribe(
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? =null
    )

    fun <T> Observable<T>.autoDisposableSubscribe(
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )

    fun <T> Flowable<T>.autoDisposableSubscribe(
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )

    class Delegate(
        private val compositeDisposable: CompositeDisposable
    ) : RxAutoDisposable {

        override fun Completable.autoDisposableSubscribe(
            onComplete: (() -> Unit)?,
            onError: ((Throwable) -> Unit)?
        ) {
            compositeDisposable += when {
                onComplete == null && onError == null -> subscribe()
                onComplete == null -> subscribe({}, onError)
                onError == null -> subscribe(onComplete)
                else -> subscribe(onComplete, onError)
            }
        }

        override fun <T> Single<T>.autoDisposableSubscribe(
            onSuccess: ((T) -> Unit)?,
            onError: ((Throwable) -> Unit)?
        ) {
            compositeDisposable += when {
                onSuccess == null && onError == null -> subscribe()
                onSuccess == null -> subscribe({}, onError)
                onError == null -> subscribe(onSuccess)
                else -> subscribe(onSuccess, onError)
            }
        }

        override fun <T> Maybe<T>.autoDisposableSubscribe(
            onSuccess: ((T) -> Unit)?,
            onError: ((Throwable) -> Unit)?,
            onComplete: (() -> Unit)?
        ) {
            compositeDisposable += when {
                onSuccess == null && onError == null && onComplete == null -> subscribe()
                onSuccess == null && onError == null -> doOnComplete(onComplete).subscribe()
                onSuccess == null && onComplete == null -> subscribe({}, onError)
                onError == null && onComplete == null -> subscribe(onSuccess)
                onSuccess == null -> subscribe({}, onError, onComplete)
                onError == null -> doOnComplete(onComplete).subscribe(onSuccess)
                onComplete == null -> subscribe(onSuccess, onError)
                else -> subscribe(onSuccess, onError, onComplete)
            }
        }

        override fun <T> Observable<T>.autoDisposableSubscribe(
            onSuccess: ((T) -> Unit)?,
            onError: ((Throwable) -> Unit)?,
            onComplete: (() -> Unit)?
        ) {
            compositeDisposable += when {
                onSuccess == null && onError == null && onComplete == null -> subscribe()
                onSuccess == null && onError == null -> doOnComplete(onComplete).subscribe()
                onSuccess == null && onComplete == null -> subscribe({}, onError)
                onError == null && onComplete == null -> subscribe(onSuccess)
                onSuccess == null -> subscribe({}, onError, onComplete)
                onError == null -> doOnComplete(onComplete).subscribe(onSuccess)
                onComplete == null -> subscribe(onSuccess, onError)
                else -> subscribe(onSuccess, onError, onComplete)
            }
        }

        override fun <T> Flowable<T>.autoDisposableSubscribe(
            onSuccess: ((T) -> Unit)?,
            onError: ((Throwable) -> Unit)?,
            onComplete: (() -> Unit)?
        ) {
            compositeDisposable += when {
                onSuccess == null && onError == null && onComplete == null -> subscribe()
                onSuccess == null && onError == null -> doOnComplete(onComplete).subscribe()
                onSuccess == null && onComplete == null -> subscribe({}, onError)
                onError == null && onComplete == null -> subscribe(onSuccess)
                onSuccess == null -> subscribe({}, onError, onComplete)
                onError == null -> doOnComplete(onComplete).subscribe(onSuccess)
                onComplete == null -> subscribe(onSuccess, onError)
                else -> subscribe(onSuccess, onError, onComplete)
            }
        }
    }
}