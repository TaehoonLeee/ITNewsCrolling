package com.example.itnewscrolling.UI.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itnewscrolling.R
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.item_news_footer_state.view.*

class NewsFooterStateAdapter(private val retry: () -> Unit,
                             private val bar: BottomAppBar
) : LoadStateAdapter<NewsFooterStateAdapter.NewsFooterStateViewHolder>() {

    override fun onBindViewHolder(holder: NewsFooterStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NewsFooterStateViewHolder {
        return NewsFooterStateViewHolder.create(parent, retry, bar)
    }

    class NewsFooterStateViewHolder private constructor(
        itemView: View,
        retry: () -> Unit,
        private val bar: BottomAppBar
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            itemView.apply {
                if (loadState is LoadState.Error) {
                    tvErrorMessage.text = loadState.error.message
                    // FAB가 Footer State의 view를 가려서 추가.
                    // 인터넷이 끊겨 Footer State View가 떠오를 시, FAB 아이콘 옆으로 이동.
                    bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                }
                else bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvErrorMessage.isVisible = loadState !is LoadState.Loading
            }
        }

        companion object {
            fun create(parent : ViewGroup, retry: () -> Unit, bar: BottomAppBar) : NewsFooterStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news_footer_state, parent, false)

                return NewsFooterStateViewHolder(view, retry, bar)
            }
        }

    }
}