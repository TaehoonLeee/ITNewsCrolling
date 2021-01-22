package com.example.itnewscrolling.Network

import com.example.itnewscrolling.Model.News
import com.example.itnewscrolling.Model.NewsList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {
    @GET("top-headlines?category=technology&apiKey=30d71f56327b4ed9a37456db3058f546&pageSize=5")
    fun getNewsList(@Query("page")page : Int) : Single<NewsList>
}