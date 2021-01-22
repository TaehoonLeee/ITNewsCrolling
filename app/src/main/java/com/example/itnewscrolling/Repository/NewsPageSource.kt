package com.example.itnewscrolling.Repository

import androidx.paging.rxjava3.RxPagingSource
import com.example.itnewscrolling.Model.News
import com.example.itnewscrolling.Network.NewsService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsPageSource @Inject constructor(
    private val newsService: NewsService
): RxPagingSource<Int, News>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, News>> {
        val page = params.key ?: 1

        return newsService.getNewsList(page)
            .subscribeOn(Schedulers.io())
            .map {
                LoadResult.Page(
                    data = it.articles,
                    prevKey = if ( page == 1) null else page-1,
                    nextKey = if ( page == it.totalResults?.toInt()!! / 5) null else page+1
                ) as LoadResult<Int, News>
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }
}