package com.escorp.movieworld.ui.uiUtils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val content: MutableList<Pair<Fragment, String>> = mutableListOf()

    override fun getItem(position: Int) = content[position].first

    override fun getCount(): Int = content.size

    fun addTab(fragment: Pair<Fragment, String>) {
        content.add(fragment)
    }

    override fun getPageTitle(position: Int): CharSequence? = content[position].second
}