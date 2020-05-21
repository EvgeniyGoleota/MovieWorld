package com.escorp.movieworld.test_unit_base

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.TrampolineScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class RxJavaExtension : BeforeAllCallback, AfterAllCallback {

    override fun beforeAll(context: ExtensionContext?) {
        RxJavaPlugins.setInitIoSchedulerHandler { TrampolineScheduler.instance() }
        RxJavaPlugins.setInitComputationSchedulerHandler { TrampolineScheduler.instance() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { TrampolineScheduler.instance() }
        RxJavaPlugins.setInitSingleSchedulerHandler { TrampolineScheduler.instance() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { TrampolineScheduler.instance() }
    }

    override fun afterAll(context: ExtensionContext?) {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}