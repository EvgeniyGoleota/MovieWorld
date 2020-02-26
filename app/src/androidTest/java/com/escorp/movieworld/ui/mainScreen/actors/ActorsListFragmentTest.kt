package com.escorp.movieworld.ui.mainScreen.actors

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.MainActivity
import com.escorp.movieworld.withViewAtPosition
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ActorsListFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkRVOnItemClick() {
        onView(allOf(withId(R.id.recycler_view), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ActorsListAdapter.ViewHolder>(0, click()))
    }

    @Test
    fun checkRVScroll() {
        onView(allOf(withId(R.id.recycler_view), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<ActorsListAdapter.ViewHolder>(19))
    }

    @Test
    fun checkRVItem() {
        onView(allOf(withId(R.id.recycler_view), isDisplayed()))
            .check(matches(withViewAtPosition(0, allOf(withId(R.id.actor_list_item), isDisplayed()))))
    }
}