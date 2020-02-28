package com.escorp.movieworld.ui.uiUtils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.escorp.movieworld.core.databinding.FragmentViewPagerBinding

abstract class BasePagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
    }

    private fun initTabs() {
        binding.pager.adapter = createPagerAdapter()
        binding.tabLayout.setupWithViewPager(binding.pager)
    }

    abstract fun createPagerAdapter(): PagerAdapter
}