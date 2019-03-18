package com.github.jershell.newsaplus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.github.jershell.newsaplus.MainActivity
import com.github.jershell.newsaplus.NewsFeedViewModel
import com.github.jershell.newsaplus.R
import com.github.jershell.newsaplus.adapters.NewsFeedRecyclerAdapter
import com.github.jershell.newsaplus.adapters.NewsFreedViewPagerAdapter
import com.github.jershell.newsaplus.data.ItemOfNews


class NewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = NewsFreedViewPagerAdapter(fragmentManager!!)
        adapter.addFragment(NewsFeedFragment(), resources.getString(R.string.news_feed_tabs_tab_first))
        adapter.addFragment(emptyFragment(), resources.getString(R.string.news_feed_tabs_tab_second))
        viewPager.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}