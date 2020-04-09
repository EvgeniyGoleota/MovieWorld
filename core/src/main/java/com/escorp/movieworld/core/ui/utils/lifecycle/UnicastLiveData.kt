package com.escorp.movieworld.core.ui.utils.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CopyOnWriteArraySet

class UnicastLiveData<T>: MutableLiveData<T>() {

    private val observers = mutableListOf<Observer<in T>>()

    private val foreverObservers = mutableListOf<Observer<in T>>()

    private val values = CopyOnWriteArraySet<T>()

    private val observerDelegate = Observer<T> { value ->
        val observer = observers.firstOrNull()

        if (observer != null && values.remove(value)) {
            observer.onChanged(value)
        }
    }

    private val foreverObserverDelegate = Observer<T> { value ->
        val observer = observers.firstOrNull()

        if (observer != null && values.remove(value)) {
            observer.onChanged(value)
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (observers.isEmpty()) {
            super.observe(owner, observerDelegate)
        }
        foreverObservers.add(observer)
    }

    override fun observeForever(observer: Observer<in T>) {
        if (foreverObservers.isEmpty()) {
            super.observeForever(foreverObserverDelegate)
        }
        foreverObservers.add(observer)
    }

    override fun removeObserver(observer: Observer<in T>) {
        if (observer === observerDelegate) {
            observers.clear()
            super.removeObserver(observer)
        }
        if (observer == foreverObserverDelegate) {
            foreverObservers.clear()
            super.removeObserver(observer)
        }

        observers.remove(observer)
        foreverObservers.remove(observer)

        if (observers.isEmpty()) {
            super.removeObserver(observerDelegate)
        }
        if (foreverObservers.isEmpty()) {
            super.removeObserver(foreverObserverDelegate)
        }
    }

    override fun setValue(value: T) {
        values.add(value)
        super.setValue(value)
    }

    override fun postValue(value: T) {
        values.add(value)
        super.postValue(value)
    }
}