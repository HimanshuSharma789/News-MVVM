package com.example.news.sources

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.API_KEY
import com.example.news.NetworkRequestStatus
import com.example.news.NewsApi
import com.example.news.model.sources.SourcesResponse
import kotlinx.coroutines.launch

class SourcesFragmentViewModel: ViewModel() {

    private val _sourcesResponse = MutableLiveData<SourcesResponse>()
    val sourcesResponse: LiveData<SourcesResponse>
        get() = _sourcesResponse

    private val _status = MutableLiveData<NetworkRequestStatus>()
    val status: LiveData<NetworkRequestStatus>
        get() = _status


    init {
        getSourcesFeed()
    }

    private fun getSourcesFeed() {
        viewModelScope.launch {
            _status.value = NetworkRequestStatus.LOADING
            try {
                _sourcesResponse.value = NewsApi.networkService.getSources("in", API_KEY)
                _status.value = NetworkRequestStatus.DONE
            } catch (t: Exception) {
                Log.v("sources feed:", t.toString())
                _status.value = NetworkRequestStatus.ERROR
            }


        }
    }

}