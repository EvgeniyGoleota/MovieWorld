package com.escorp.movieworld.rule

import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavDeepLinkBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.escorp.movieworld.MainActivity
import com.escorp.movieworld.MovieWorldApplication

fun launchFragmentRule(
    destinationId: Int,
    graphId: Int,
    args: Bundle? = null
): LaunchFragmentRule = LaunchFragmentRule(destinationId, graphId, args)

class LaunchFragmentRule(
    private val destinationId: Int,
    private val graphId: Int,
    private val args: Bundle?
) : IntentsTestRule<MainActivity>(MainActivity::class.java)  {
    override fun getActivityIntent(): Intent =
        NavDeepLinkBuilder(ApplicationProvider.getApplicationContext<MovieWorldApplication>())
            .setGraph(graphId)
            .setDestination(destinationId)
            .setArguments(args)
            .createTaskStackBuilder()
            .intents
            .first()
}