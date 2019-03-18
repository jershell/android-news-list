package com.github.jershell.newsaplus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jershell.newsaplus.MainActivity
import com.github.jershell.newsaplus.NewsFeedViewModel
import com.github.jershell.newsaplus.R
import com.github.jershell.newsaplus.adapters.NewsFeedRecyclerAdapter
import com.github.jershell.newsaplus.data.ItemOfNews
import com.squareup.picasso.Picasso

class NewsDetailFragment : Fragment() {
    private lateinit var viewModel: NewsFeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val list = view!!.findViewById<RecyclerView>(R.id.fragment_news_detail_list)
        val mainActivity = (activity as MainActivity)

        list.layoutManager = LinearLayoutManager(this.context)

        viewModel = ViewModelProviders.of(this.activity as MainActivity).get(NewsFeedViewModel::class.java)

        val position: Int? = arguments?.getInt("itemPosition", -1)

        if (position != null && position != -1) {
            val newsItem: ItemOfNews = viewModel.news.value!![position]
            list.adapter = NewsFeedRecyclerAdapter(listOf(newsItem), this.context!!, single = true)
        }


    }
}