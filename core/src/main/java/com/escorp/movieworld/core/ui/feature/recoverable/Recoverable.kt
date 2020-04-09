package com.escorp.movieworld.core.ui.feature.recoverable

import io.reactivex.Observable

interface Recoverable {
    fun observeThrowables(): Observable<Throwable> = Observable.empty()
    fun sendEvent(event: Event): Boolean

    sealed class Event(open val throwable: Throwable) {
        data class Retry(override val throwable: Throwable) : Event(throwable)
        data class Cancel(override val throwable: Throwable) : Event(throwable)
    }
}