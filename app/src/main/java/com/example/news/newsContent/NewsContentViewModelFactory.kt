package com.example.news.newsContent

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.model.category.Article

class NewsContentViewModelFactory(
    private val article: Article,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsContentViewModel::class.java)) {
            return NewsContentViewModel(article, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}