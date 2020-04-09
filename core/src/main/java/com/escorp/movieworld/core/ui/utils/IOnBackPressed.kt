package com.escorp.movieworld.core.ui.utils

interface IOnBackPressed {
    /**
     * @return true if the activity should perform the default action onBackPressed(),
     * false if the action was handled.
     */
    fun onBackPressed(): Boolean
}