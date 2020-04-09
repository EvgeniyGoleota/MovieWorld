package com.escorp.movieworld.core.ui.utils.rx

import com.escorp.movieworld.core.ui.feature.recoverable.Recoverable
import io.reactivex.*
import io.reactivex.functions.Function
import io.reactivex.subjects.PublishSubject
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.CopyOnWriteArrayList

interface RxRecoverable : Recoverable {

    fun Completable.toRecoverable(): Completable

    fun Completable.toRecoverable(
        block: (Throwable) -> Completable
    ): Completable

    fun <T> Single<T>.toRecoverable(
        fallbackValue: T
    ): Single<T>

    fun <T> Single<T>.toRecoverable(
        block: (Throwable) -> Single<T>
    ): Single<T>

    fun <T> Maybe<T>.toRecoverable(
        fallbackValue: T? = null
    ): Maybe<T>

    fun <T> Maybe<T>.toRecoverable(
        block: (Throwable) -> Maybe<T>
    ): Maybe<T>

    fun <T> Observable<T>.toRecoverable(
        fallbackValue: T? = null
    ): Observable<T>

    fun <T> Observable<T>.toRecoverable(
        block: (Throwable) -> Observable<T>
    ): Observable<T>

    fun <T> Flowable<T>.toRecoverable(
        fallbackValue: T? = null
    ): Flowable<T>

    fun <T> Flowable<T>.toRecoverable(
        block: (Throwable) -> Flowable<T>
    ): Flowable<T>

    class Delegate : RxRecoverable {

        private val unrecoveredThrowables = CopyOnWriteArrayList<Throwable>()

        private val throwables = PublishSubject.create<Throwable>()

        private val recoverableEvents = PublishSubject.create<Recoverable.Event>()

        override fun observeThrowables(): Observable<Throwable> {
            return throwables
        }

        @TestOnly
        fun getUnrecoveredThrowables() = unrecoveredThrowables

        @Suppress("PlatformExtensionReceiverOfInline")
        override fun sendEvent(event: Recoverable.Event): Boolean {
            val throwable = unrecoveredThrowables.firstOrNull { throwable ->
                throwable.stackTrace.contentEquals(event.throwable.stackTrace)
            }

            return if (throwable == null) {
                false
            } else {
                unrecoveredThrowables.remove(throwable)
                recoverableEvents.onNext(event)
                true
            }
        }

        override fun Completable.toRecoverable(): Completable {
            return this.toRecoverable(block = {
                Completable.complete()
            })
        }

        override fun Completable.toRecoverable(block: (Throwable) -> Completable): Completable {
            return this
                .doOnError { throwable ->
                    unrecoveredThrowables.add(throwable)
                    throwables.onNext(throwable)
                }
                .retryWhen {
                    recoverableEvents
                        .toFlowable(BackpressureStrategy.LATEST)
                        .flatMap { recoverableEvent ->
                            when (recoverableEvent) {
                                is Recoverable.Event.Retry -> Flowable.just(Unit)
                                is Recoverable.Event.Cancel -> Flowable.error(recoverableEvent.throwable)
                            }
                        }
                }
                .onErrorResumeNext(block)
        }

        override fun <T> Single<T>.toRecoverable(
            fallbackValue: T
        ): Single<T> {
            return this.toRecoverable(block = {
                Single.just(fallbackValue)
            })
        }

        override fun <T> Single<T>.toRecoverable(
            block: (Throwable) -> Single<T>
        ): Single<T> {
            return this
                .doOnError { throwable ->
                    unrecoveredThrowables.add(throwable)
                    throwables.onNext(throwable)
                }
                .retryWhen {
                    recoverableEvents
                        .toFlowable(BackpressureStrategy.LATEST)
                        .flatMap { recoverableEvent ->
                            when (recoverableEvent) {
                                is Recoverable.Event.Retry -> Flowable.just(Unit)
                                is Recoverable.Event.Cancel -> Flowable.error(recoverableEvent.throwable)
                            }
                        }
                }
                .onErrorResumeNext(block)
        }

        override fun <T> Maybe<T>.toRecoverable(
            fallbackValue: T?
        ): Maybe<T> {
            return this.toRecoverable(block = { throwable ->
                if (fallbackValue == null) {
                    Maybe.empty()
                } else {
                    Maybe.error(throwable)
                }
            })
        }

        override fun <T> Maybe<T>.toRecoverable(
            block: (Throwable) -> Maybe<T>
        ): Maybe<T> {
            return this
                .doOnError { throwable ->
                    unrecoveredThrowables.add(throwable)
                    throwables.onNext(throwable)
                }
                .retryWhen {
                    recoverableEvents
                        .toFlowable(BackpressureStrategy.LATEST)
                        .flatMap { recoverableEvent ->
                            when (recoverableEvent) {
                                is Recoverable.Event.Retry -> Flowable.just(Unit)
                                is Recoverable.Event.Cancel -> Flowable.error(recoverableEvent.throwable)
                            }
                        }
                }
                .onErrorResumeNext(Function(block))
        }

        override fun <T> Observable<T>.toRecoverable(
            fallbackValue: T?
        ): Observable<T> {
            return this.toRecoverable(block = {
                if (fallbackValue == null) {
                    Observable.empty()
                } else {
                    Observable.just(fallbackValue)
                }
            })
        }

        override fun <T> Observable<T>.toRecoverable(
            block: (Throwable) -> Observable<T>
        ): Observable<T> {
            return this
                .doOnError { throwable ->
                    unrecoveredThrowables.add(throwable)
                    throwables.onNext(throwable)
                }
                .retryWhen {
                    recoverableEvents.flatMap { recoverableEvent ->
                        when (recoverableEvent) {
                            is Recoverable.Event.Retry -> Observable.just(Unit)
                            is Recoverable.Event.Cancel -> Observable.error(recoverableEvent.throwable)
                        }
                    }
                }
                .onErrorResumeNext(Function(block))
        }

        override fun <T> Flowable<T>.toRecoverable(fallbackValue: T?): Flowable<T> {
            return this.toRecoverable(block = {
                if (fallbackValue == null) {
                    Flowable.empty()
                } else {
                    Flowable.just(fallbackValue)
                }
            })
        }

        override fun <T> Flowable<T>.toRecoverable(
            block: (Throwable) -> Flowable<T>
        ): Flowable<T> {
            return this
                .doOnError { throwable ->
                    unrecoveredThrowables.add(throwable)
                    throwables.onNext(throwable)
                }
                .retryWhen {
                    recoverableEvents
                        .toFlowable(BackpressureStrategy.LATEST)
                        .flatMap { recoverableEvent ->
                            when (recoverableEvent) {
                                is Recoverable.Event.Retry -> Flowable.just(Unit)
                                is Recoverable.Event.Cancel -> Flowable.error(recoverableEvent.throwable)
                            }
                        }
                }
                .onErrorResumeNext(Function(block))
        }
    }
}