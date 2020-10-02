package com.escorp.movieworld.ui.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.escorp.movieworld.R
import com.escorp.movieworld.rule.launchFragmentRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ActorListScreenTest {

    @get:Rule
    val fragmentRule = launchFragmentRule(R.id.actorListFragment, R.id.actor_navigation)

    @Test
    fun actorListTest() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }
}