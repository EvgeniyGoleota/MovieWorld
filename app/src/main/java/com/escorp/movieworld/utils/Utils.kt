package com.escorp.movieworld.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

fun isIdValid(id: Int?) = id != null && id != -1

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(
    factory: ViewModelProvider.Factory
) = ViewModelProviders.of(this, factory)[T::class.java]

inline fun <reified T : ViewModel> Fragment.injectViewModel(
    factory: ViewModelProvider.Factory
) = ViewModelProviders.of(this, factory)[T::class.java]

fun isNetworkConnected(context: Context): Boolean =
    (context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?).let {
        it?.activeNetworkInfo != null && it.activeNetworkInfo.isConnected
    }