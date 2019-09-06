package com.escorp.movieworld.utils

class NetworkState(val status: Status, val msg: String) {

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILD
    }

    companion object {
        val LOADED = NetworkState(Status.SUCCESS, "success")
        val LOADING = NetworkState(Status.RUNNING, "running")
    }
}