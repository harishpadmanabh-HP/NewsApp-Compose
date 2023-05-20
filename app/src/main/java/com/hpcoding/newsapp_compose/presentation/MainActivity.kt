package com.hpcoding.newsapp_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hpcoding.newsapp_compose.presentation.NewsViewModel
import com.hpcoding.newsapp_compose.presentation.navigation.NewsAppNavGraph
import com.hpcoding.newsapp_compose.presentation.theme.NewsAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<NewsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppComposeTheme {
                NewsApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NewsApp(viewModel: NewsViewModel) {
    NewsAppNavGraph(viewModel = viewModel)
}
