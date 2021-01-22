package com.example.itnewscrolling.UI.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.itnewscrolling.Glide.GlideApp
import com.example.itnewscrolling.Model.News
import com.example.itnewscrolling.R
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter : PagingDataAdapter<News, NewsAdapter.NewsViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        news?.let {
            holder.bind(it)
        }
    }

    class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news : News) {
            itemView.apply {
                GlideApp.with(ivUrlToImage)
                    .load(news.urlToImage)
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(ivUrlToImage)

                tvTitle.text = news.title
                tvUrl.text = news.url
                tvDescription.text = news.description
            }
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_news, parent, false)

                return NewsViewHolder(itemView)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.title.equals(newItem.title)
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }

        }
    }
}