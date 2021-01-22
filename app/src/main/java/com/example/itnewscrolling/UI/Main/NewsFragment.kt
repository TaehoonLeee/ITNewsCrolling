package com.example.itnewscrolling.UI.Main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itnewscrolling.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_fragment.*

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.news_fragment) {

    private lateinit var adapter: NewsAdapter
    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter()
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsViewModel.NewsList.observe(viewLifecycleOwner, Observer {
            adapter.setNewses(it)
        })
    }

}