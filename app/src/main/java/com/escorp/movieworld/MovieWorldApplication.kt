package com.escorp.movieworld

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import com.escorp.movieworld.dagger.AppComponent
import com.escorp.movieworld.dagger.AppModule
import com.escorp.movieworld.dagger.DaggerAppComponent
import javax.inject.Inject

class MovieWorldApplication : Application() {

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate() {
        injectDependencies()
        initActivityLifecycleCallbacks()
        super.onCreate()
    }

    private fun injectDependencies() {
        appComponent = createAppComponent().apply {
                inject(this@MovieWorldApplication)
            }
    }

    protected open fun createAppComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    private fun initActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(OnActivityCreatedLifecycleCallbacks {
            (it as? FragmentActivity?)?.let { fragmentActivity ->
                fragmentActivity.supportFragmentManager.fragmentFactory = fragmentFactory
            }
        })
    }
}