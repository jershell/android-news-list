package com.github.jershell.newsaplus.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.jershell.newsaplus.MainActivity
import com.github.jershell.newsaplus.models.NewsFeedViewModel
import com.github.jershell.newsaplus.R
import com.github.jershell.newsaplus.adapters.NewsFeedRecyclerAdapter
import com.github.jershell.newsaplus.adapters.OnItemClickListener
import com.github.jershell.newsaplus.adapters.addOnItemClickListener
import kotlinx.android.synthetic.main.fragment_news_feed.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NewsFeedFragment : Fragment() {
    companion object {
        fun newInstance() = NewsFeedFragment()
    }

    private lateinit var viewModel: NewsFeedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.github.jershell.newsaplus.R.layout.fragment_news_feed, container, false)
    }

    override fun onResume() {
        super.onResume()
        Log.d("NewsFeedFragment", "onResume()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this.activity as MainActivity).get(NewsFeedViewModel::class.java)

        val swipe = view!!.findViewById<SwipeRefreshLayout>(R.id.news_feed_swipe_refresh)
        val list = view!!.findViewById<RecyclerView>(R.id.news_feed_list)
        val mainActivity = (activity as MainActivity)
        list.layoutManager = LinearLayoutManager(this.context)

        list.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                mainActivity.navigateToDetail(position)
            }
        })

        swipe.setOnRefreshListener {
            mainActivity.ifOnline {
                GlobalScope.launch(Dispatchers.Main) {
                    viewModel.fetch(force = true)
                }
            }
        }


        viewModel.isPending.observe(this, Observer {
            swipe.isRefreshing = it

            news_feed_list.visibility = if (it) {
                View.GONE
            } else {
                View.VISIBLE
            }
        })

        viewModel.news.observe(this, Observer {
            list.adapter = NewsFeedRecyclerAdapter(it, this.context!!)
        })

        mainActivity.ifOnline {
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.fetch()
            }
        }
    }
}
