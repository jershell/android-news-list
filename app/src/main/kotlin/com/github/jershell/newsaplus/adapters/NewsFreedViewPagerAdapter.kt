package com.github.jershell.newsaplus.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NewsFreedViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {
    private val mFragmentList = emptyList<Fragment>()
    private val mFragmentTitleList = emptyList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.plus(fragment)
        mFragmentTitleList.plus(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
}