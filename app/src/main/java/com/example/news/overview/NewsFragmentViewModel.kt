package com.example.news.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.API_KEY
import com.example.news.NewsApi
import com.example.news.model.Article
import com.example.news.model.NewsResponse
import kotlinx.coroutines.launch

enum class NetworkRequestStatus{LOADING, ERROR, DONE}

class NewsFragmentViewModel(category: String) : ViewModel() {

    private val _status = MutableLiveData<NetworkRequestStatus>()
    val status: LiveData<NetworkRequestStatus>
        get() = _status

    private val _newsResponse = MutableLiveData<NewsResponse>()
    val newsResponse: LiveData<NewsResponse>
        get() = _newsResponse

    private var _navigateToSelectedNews = MutableLiveData<Article?>()
    val navigateToSelectedNews: LiveData<Article?>
        get() = _navigateToSelectedNews

    init {
        getNewsFeed(category)
    }

    private fun getNewsFeed(category: String) {
        viewModelScope.launch {
            _status.value = NetworkRequestStatus.LOADING
            try {
                _newsResponse.value =
                    NewsApi.networkService.getTopHeadlines("in", category, API_KEY)
                _status.value = NetworkRequestStatus.DONE
            } catch (t: Exception) {
                Log.v("yoyo", t.toString())
                _status.value = NetworkRequestStatus.ERROR
            }
        }
    }

    fun displayNewsContent(article: Article) {
        _navigateToSelectedNews.value = article
    }

    fun displayNewsContentComplete() {
        _navigateToSelectedNews.value = null
    }


}