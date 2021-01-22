package com.example.itnewscrolling.UI.Main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itnewscrolling.R
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.news_fragment.*

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.news_fragment) {

    private lateinit var adapter: NewsAdapter
    private val newsViewModel by viewModels<NewsViewModel>()
    private lateinit var bar : BottomAppBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.bar = requireActivity().bar
        adapter = NewsAdapter()

        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.adapter = adapter
        rvNews.adapter = adapter.withLoadStateFooter(
            NewsFooterStateAdapter(retry = { adapter.retry() }, bar = bar)
        )

        adapter.addLoadStateListener { loadStates ->
            srl.isRefreshing = loadStates.source.refresh is LoadState.Loading
            llErrorContainer.isVisible = loadStates.source.refresh is LoadState.Error

            rvNews.isVisible = !llErrorContainer.isVisible

            if(loadStates.source.refresh is LoadState.Error) {
                btnRetry.setOnClickListener{
                    adapter.retry()
                    bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER)
                }
                llErrorContainer.isVisible = loadStates.source.refresh is LoadState.Error
                val errorMsg = (loadStates.source.refresh as LoadState.Error).error.message
                tvErrorMessage.text = errorMsg
            }
        }
        srl.setOnRefreshListener {
            newsViewModel.onRefresh()
        }

        // Add item to recyclerView
        newsViewModel.NewsList.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })

        // bottom app bar show / hide by moving recyclerview in fragment.
        rvNews.setOnScrollChangeListener { _, _, dy, _, oldDy ->
            if (dy - oldDy > 0) bar.performHide()
            else bar.performShow()
        }
    }
}