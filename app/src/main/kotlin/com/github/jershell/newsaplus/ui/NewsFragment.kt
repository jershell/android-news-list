package com.github.jershell.newsaplus.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.jershell.newsaplus.R
import com.github.jershell.newsaplus.adapters.NewsFreedViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class NewsFragment : Fragment() {
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = NewsFreedViewPagerAdapter(childFragmentManager!!)
        adapter.addFragment(NewsFeedFragment(), resources.getString(R.string.news_feed_tabs_tab_first))
        adapter.addFragment(EmptyFragment(), resources.getString(R.string.news_feed_tabs_tab_second))
        viewPager.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewPager = view?.findViewById(R.id.news_feed_view_pager)
        setupViewPager(viewPager!!)

        tabLayout = view?.findViewById(R.id.news_feed_tabs)
        tabLayout!!.setupWithViewPager(viewPager)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("NewsFragment", "onDetach()")
    }
}