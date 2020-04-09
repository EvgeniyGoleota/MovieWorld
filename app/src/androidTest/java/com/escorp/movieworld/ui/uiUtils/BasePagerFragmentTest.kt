package com.escorp.movieworld.ui.uiUtils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.escorp.movieworld.R
import com.escorp.movieworld.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BasePagerFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)

    @Test
    fun checkTabLayoutDisplayed() {
        onView(withId(R.id.tab_layout))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    @Test
    fun swipePage() {
        onView(withId(R.id.pager)).check(matches(isDisplayed()))
        onView(withId(R.id.pager)).perform(swipeLeft())
    }
}