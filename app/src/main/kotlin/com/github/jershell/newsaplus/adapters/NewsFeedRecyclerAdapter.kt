package com.github.jershell.newsaplus.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jershell.newsaplus.R
import com.github.jershell.newsaplus.data.ItemOfNews
import com.squareup.picasso.Picasso


interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })
}


class NewsFeedRecyclerAdapter(private val items: List<ItemOfNews>, val context: Context, val single: Boolean = false) :
    RecyclerView.Adapter<NewsFeedRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(items[position].pic).into(holder.pic)
        holder.date.text = items[position].date
        holder.title.text = items[position].title
        holder.description.text = items[position].description
        if(single) {
            holder.description.maxLines = Integer.MAX_VALUE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pic = itemView.findViewById<ImageView>(R.id.news_item_image)!!
        val date = itemView.findViewById<TextView>(R.id.news_item_date)!!
        val title = itemView.findViewById<TextView>(R.id.news_item_title)!!
        val description = itemView.findViewById<TextView>(R.id.news_item_description)!!
    }
}
