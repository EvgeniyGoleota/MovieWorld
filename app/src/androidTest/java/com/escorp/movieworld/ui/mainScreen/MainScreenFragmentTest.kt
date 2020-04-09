package com.escorp.movieworld.ui.mainScreen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.escorp.movieworld.*
import com.escorp.movieworld.MainActivity
import org.hamcrest.Matchers.allOf

import org.junit.Rule
import org.junit.Test

class MainScreenFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)

    @Test
    fun checkTabAndPageCount() {
        onView(withId(R.id.pager)).check(matches(withPageCount(2)))
        onView(withId(R.id.tab_layout)).check(matches(withTabCount(2)))
    }

    @Test
    fun checkTabTitles() {
        onView(allOf(withText(R.string.title_actors), isDescendantOfA(withId(R.id.tab_layout))))
            .perform(click())
            .check(matches(isDisplayed()))

        onView(allOf(withText(R.string.title_movies), isDescendantOfA(withId(R.id.tab_layout))))
            .perform(click())
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkFirstTab() {
        onView(allOf(withText(R.string.title_actors), isDescendantOfA(withId(R.id.tab_layout))))
            .perform(click())
            .check(matches(isDisplayed()))
        onView(withId(R.id.pager)).check(matches(isActorsListFragment()))
    }

    @Test
    fun checkSecondTab() {
        onView(allOf(withText(R.string.title_movies), isDescendantOfA(withId(R.id.tab_layout))))
            .perform(click())
            .check(matches(isDisplayed()))
        onView(withId(R.id.pager)).check(matches(isMoviesListFragment()))
    }
}