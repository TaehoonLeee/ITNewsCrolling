package com.example.itnewscrolling.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.itnewscrolling.Model.News
import com.example.itnewscrolling.Model.NewsList
import com.example.itnewscrolling.Network.NewsService
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val newsPageSource: NewsPageSource
) {
    fun getNewsList() : Flowable<PagingData<News>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { newsPageSource }
        ).flowable
    }
}