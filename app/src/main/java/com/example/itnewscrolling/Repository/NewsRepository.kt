package com.example.itnewscrolling.Repository

import com.example.itnewscrolling.Model.News
import com.example.itnewscrolling.Model.NewsList
import com.example.itnewscrolling.Network.NewsService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsService: NewsService
) {
    fun getNewsList() : Single<NewsList> {
        return newsService.getNewsList()
            .subscribeOn(Schedulers.io())
    }
}