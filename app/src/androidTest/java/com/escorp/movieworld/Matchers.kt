package com.escorp.movieworld

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.viewpager.widget.ViewPager
import com.escorp.movieworld.ui.mainScreen.actors.ActorsListFragment
import com.escorp.movieworld.ui.mainScreen.movies.MoviesListFragment
import com.escorp.movieworld.ui.uiUtils.PagerAdapter
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Description
import org.hamcrest.Matcher

fun withTabCount(count: Int): Matcher<View> =
    object : BoundedMatcher<View, TabLayout>(TabLayout::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("TabLayout with tab count: $count")
        }

        override fun matchesSafely(item: TabLayout?) = item?.tabCount == count
    }

fun withPageCount(count: Int): Matcher<View> =
    object : BoundedMatcher<View, ViewPager>(ViewPager::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("ViewPager with page count: $count")
        }

        override fun matchesSafely(item: ViewPager?) = item?.adapter?.count == count
    }

fun isActorsListFragment(): Matcher<View> =
    object : BoundedMatcher<View, ViewPager>(ViewPager::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("Check ViewPager displayed is ActorsListFragment fragment")
        }

        override fun matchesSafely(item: ViewPager?) =
            item?.let { (it.adapter as PagerAdapter?)?.getItem(it.currentItem) is ActorsListFragment } ?: false
    }

fun isMoviesListFragment(): Matcher<View> =
    object : BoundedMatcher<View, ViewPager>(ViewPager::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("Check ViewPager displayed is MoviesListFragment fragment")
        }

        override fun matchesSafely(item: ViewPager?) =
            item?.let { (it.adapter as PagerAdapter?)?.getItem(it.currentItem) is MoviesListFragment } ?: false
    }

fun withViewAtPosition(position: Int, itemMatcher: Matcher<View>) = object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description?) {
        itemMatcher.describeTo(description)
    }

    override fun matchesSafely(item: RecyclerView?) =
        item?.let { recyclerView ->
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
            viewHolder != null && itemMatcher.matches(viewHolder.itemView)
        } ?: false
}