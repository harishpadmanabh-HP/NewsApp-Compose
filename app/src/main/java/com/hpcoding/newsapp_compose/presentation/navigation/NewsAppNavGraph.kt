package com.hpcoding.newsapp_compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hpcoding.newsapp_compose.presentation.NewsViewModel
import com.hpcoding.newsapp_compose.presentation.screens.NewsDetails
import com.hpcoding.newsapp_compose.presentation.screens.NewsListScreen

object Routes {
    const val NewsList = "/list"
    const val NewsDetails = "/details"
}

@Composable
fun NewsAppNavGraph(
    viewModel: NewsViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.NewsList) {
        composable(Routes.NewsList) {
            val searchQuery by viewModel.searchQuery.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            NewsListScreen(
                isLoading = isLoading,
                news = viewModel.news,
                search = searchQuery,
                onSearchChange = { query ->
                    viewModel.onSearchQueryChange(query)
                },
                onSearch = {
                    viewModel.getNews()
                }, onItemSelected = { item ->
                    viewModel.newsDetailItem = item
                    navController.navigate(Routes.NewsDetails)
                })
        }
        composable(Routes.NewsDetails) {
            NewsDetails(newsItem = viewModel.newsDetailItem)
        }
    }

}