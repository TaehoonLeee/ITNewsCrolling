package com.example.itnewscrolling.UI.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itnewscrolling.Model.News
import com.example.itnewscrolling.R
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsList : List<News> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    fun setNewses(newsList : List<News>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news : News) {
            itemView.apply {
                tvTitle.text = news.name
                tvId.text = news.id
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
}