package com.example.news.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsFragmentViewModelFactory (
    private val category: String,
    private val source: String
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsFragmentViewModel::class.java)) {
                return NewsFragmentViewModel(category, source) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}