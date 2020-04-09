package com.escorp.movieworld.core.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.escorp.movieworld.core.ui.utils.rx.RxAutoDisposable
import com.escorp.movieworld.core.ui.utils.rx.RxRecoverable
import com.escorp.movieworld.core.ui.utils.rx.RxSchedulable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

abstract class ViewModelBase(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel(),
    RxAutoDisposable by RxAutoDisposable.Delegate(compositeDisposable),
    RxSchedulable by RxSchedulable.Delegate(),
    RxRecoverable by RxRecoverable.Delegate() {

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    protected fun <T> Observable<T>.toAutoDisposableLiveData(): LiveData<T> =
        MutableLiveData<T>().apply {
            observeOn(AndroidSchedulers.mainThread()).autoDisposableSubscribe(::setValue)
        }
}