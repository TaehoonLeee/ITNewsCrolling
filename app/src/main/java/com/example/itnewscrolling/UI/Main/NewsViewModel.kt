package com.example.itnewscrolling.UI.Main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn

import com.example.itnewscrolling.Model.News
import com.example.itnewscrolling.Repository.NewsRepository


class NewsViewModel @ViewModelInject constructor(
    val newsRepository : NewsRepository
): ViewModel() {

    private val _NewsList = MutableLiveData<PagingData<News>>()
    val NewsList : LiveData<PagingData<News>>
        get() = _NewsList

    init {
        onGetNewsList()
    }

    fun onRefresh() {
        onGetNewsList()
    }

    private fun onGetNewsList() {
        newsRepository.getNewsList()
            .cachedIn(viewModelScope)
            .subscribe{ _NewsList.value = it }
    }
}