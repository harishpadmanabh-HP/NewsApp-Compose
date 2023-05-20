package com.hpcoding.newsapp_compose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpcoding.newsapp_compose.domain.GetNewsUseCase
import com.hpcoding.newsapp_compose.domain.NewsItem
import com.hpcoding.newsapp_compose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    var news by mutableStateOf(emptyList<NewsItem>())

    var newsDetailItem by mutableStateOf<NewsItem?>(null)

    fun getNews() {
        viewModelScope.launch {
            getNewsUseCase.getNewsList(searchQuery.value).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> _isLoading.value = true
                    is Resource.Success -> {
                        _isLoading.value = false
                        news = result.data ?: emptyList()
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                        _message.value = result.message ?: "Something went wrong."
                    }
                }
            }
        }
    }

    init {
        getNews()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        if (query.isEmpty())
            getNews()
    }

}