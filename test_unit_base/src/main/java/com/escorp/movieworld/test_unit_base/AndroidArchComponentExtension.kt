package com.escorp.movieworld.test_unit_base

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class AndroidArchComponentExtension : BeforeAllCallback, AfterAllCallback {

    @SuppressLint("RestrictedApi")
    override fun beforeAll(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(TrampolineTaskExecutor())
    }

    @SuppressLint("RestrictedApi")
    override fun afterAll(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @SuppressLint("RestrictedApi")
    private class TrampolineTaskExecutor : TaskExecutor() {

        override fun executeOnDiskIO(runnable: Runnable) {
            runnable.run()
        }

        override fun isMainThread(): Boolean {
            return true
        }

        override fun postToMainThread(runnable: Runnable) {
            runnable.run()
        }
    }
}