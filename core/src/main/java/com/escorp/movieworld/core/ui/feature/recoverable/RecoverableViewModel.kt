package com.escorp.movieworld.core.ui.feature.recoverable

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.escorp.movieworld.core.ui.utils.lifecycle.UnicastLiveData
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RecoverableViewModel @Inject constructor() : ViewModel(), Recoverable {

    private val compositeDisposable = CompositeDisposable()

    private var unrecoveredThrowables = PublishSubject.create<Throwable>()

    private var recoverables = mutableListOf<Recoverable>()

    val unrecoveredThrowableLiveData: LiveData<Throwable> = UnicastLiveData<Throwable>().apply {
        unrecoveredThrowables
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::setValue)
            .addTo(compositeDisposable)
    }

    override fun sendEvent(event: Recoverable.Event): Boolean {
        return recoverables.any { recoverable ->
            recoverable.sendEvent(event)
        }
    }

    fun addRecoverable(recoverable: Recoverable) {
        if (recoverables.contains(recoverable).not()) {
            recoverables.add(recoverable)

            recoverable
                .observeThrowables()
                .flatMapMaybe { throwable ->
                    /* Example:
                    if (throwable is DataException.HTTP.Unauthorized) {
                        logoutUserUseCase()
                            .andThen(router.logout())
                            .onErrorComplete()
                            .toMaybe<Throwable>()
                    } else {*/
                        Maybe.just(throwable)
                    /*}*/
                }
                .subscribe(unrecoveredThrowables::onNext)
                .addTo(compositeDisposable)
        }
    }

    override fun onCleared() {
        recoverables.clear()
        compositeDisposable.clear()
    }

    companion object {

        fun get(
            viewModelStoreOwner: ViewModelStoreOwner,
            viewModelFactory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
        ): RecoverableViewModel {
            return ViewModelProvider(
                viewModelStoreOwner,
                viewModelFactory
            ).get(RecoverableViewModel::class.java)
        }
    }
}