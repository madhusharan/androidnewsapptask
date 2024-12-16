package com.newsfeed.app.viewmodel

import NewsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsfeed.app.roomdb.NewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsArticles = MutableLiveData<List<NewsArticle>>()
    val newsArticles: LiveData<List<NewsArticle>> get() = _newsArticles

    fun fetchNews(category: String) {
        viewModelScope.launch {
            val articles = newsRepository.fetchNews(category, "6gybdnje8bwkjocsdfglgjrem")
            _newsArticles.postValue(articles)
        }
    }

    fun getSavedArticles(): LiveData<List<NewsArticle>> {
        return newsRepository.getAllNews()
    }
}

