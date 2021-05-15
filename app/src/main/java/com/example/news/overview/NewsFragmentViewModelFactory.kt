package com.example.news.overview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.model.Article
import com.example.news.newsContent.NewsContentViewModel

class NewsFragmentViewModelFactory (
    private val category: String
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsFragmentViewModel::class.java)) {
                return NewsFragmentViewModel(category) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}