package com.escorp.movieworld.core.activityprovider

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.escorp.movieworld.core.router.NavigationControllerHost
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.UnicastSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RxActivityProvider @Inject constructor(
    application: Application
) {

    private var blockUnicastSubject = UnicastSubject.create<AppCompatActivity.() -> Unit>()

    private var blockDisposable: Disposable? = null

    private val activityLifecycleCallbacks: SimpleActivityLifecycleCallbacks

    init {
        activityLifecycleCallbacks =
            object : SimpleActivityLifecycleCallbacks() {
                override fun onActivityResumed(activity: Activity) {
                    if (activity is AppCompatActivity) {
                        synchronized(this@RxActivityProvider) {
                            blockDisposable =
                                blockUnicastSubject
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe { activity.it() }
                        }
                    }
                }

                override fun onActivityPaused(activity: Activity) {
                    if (activity is AppCompatActivity) {
                        synchronized(this@RxActivityProvider) {
                            blockDisposable?.dispose()
                            blockDisposable = null
                            blockUnicastSubject = UnicastSubject.create()
                        }
                    }
                }
            }
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    fun submitBlock(block: AppCompatActivity.() -> Unit): Completable {
        return Completable.fromRunnable {
            synchronized(this) {
                blockUnicastSubject.onNext(block)
            }
        }
    }

    fun submitNavigation(navigation: NavController.() -> Unit): Completable {
        return submitBlock {
            if (this is NavigationControllerHost) {
                navController.navigation()
            }
        }
    }
}