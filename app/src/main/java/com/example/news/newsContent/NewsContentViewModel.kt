package com.example.news.newsContent

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.model.category.Article

class NewsContentViewModel(article: Article, application: Application) : ViewModel() {

    private val _newsContent = MutableLiveData<Article>()
    val newsContent: LiveData<Article>
        get() = _newsContent

    init {
        _newsContent.value = article
    }


}