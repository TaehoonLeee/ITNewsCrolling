package com.example.itnewscrolling.UI.Main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itnewscrolling.Model.News
import com.example.itnewscrolling.Repository.NewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

class NewsViewModel @ViewModelInject constructor(
    newsRepository : NewsRepository
): ViewModel() {
    private val _NewsList = MutableLiveData<List<News>>()
    val NewsList : LiveData<List<News>>
        get() = _NewsList

    init {
        newsRepository.getNewsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { newsList -> _NewsList.value = newsList.sources}, { e -> Timber.e(e) })
    }
}