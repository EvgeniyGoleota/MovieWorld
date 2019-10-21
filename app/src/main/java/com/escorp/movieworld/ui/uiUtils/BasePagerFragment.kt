package com.escorp.movieworld.ui.uiUtils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.escorp.movieworld.databinding.FragmentViewPagerBinding
import kotlinx.android.synthetic.main.fragment_view_pager.*

abstract class BasePagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentViewPagerBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
    }

    private fun initTabs() {
        pager.adapter = createPagerAdapter()
        tab_layout.setupWithViewPager(pager)
    }

    abstract fun createPagerAdapter(): PagerAdapter
}