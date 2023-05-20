package com.hpcoding.newsapp_compose.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hpcoding.newsapp_compose.domain.NewsItem
import com.hpcoding.newsapp_compose.presentation.NewsViewModel
import com.hpcoding.newsapp_compose.presentation.components.EmptyListView
import com.hpcoding.newsapp_compose.presentation.components.ListHeader
import com.hpcoding.newsapp_compose.presentation.components.LoadingView
import com.hpcoding.newsapp_compose.presentation.components.NewsCard
import com.hpcoding.newsapp_compose.presentation.theme.Olive

@Composable
fun NewsListScreen(
    isLoading: Boolean,
    news: List<NewsItem>,
    search: String,
    onSearchChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onItemSelected: (NewsItem) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Olive)
        ) {
            ListHeader(query = search, onSearchQueryChange = {
                onSearchChange(it)
            }, onSearch = {
                onSearch(it)
            }, modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .wrapContentHeight()
            )
        }

        Crossfade(targetState = isLoading) { isLoadingNow ->
            if (isLoadingNow)
                LoadingView()
            else {
                Crossfade(targetState = !isLoading && news.isEmpty()) { showEmpty ->
                    if (showEmpty)
                        EmptyListView()
                    else {
                        LazyColumn(
                            contentPadding = PaddingValues(12.dp)
                        ) {
                            items(news) {
                                NewsCard(
                                    item = it,
                                    onClick = { item ->
                                        onItemSelected(item)
                                    },
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )
                            }
                        }
                    }
                }


            }
        }


    }

}