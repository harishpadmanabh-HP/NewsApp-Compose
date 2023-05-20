package com.hpcoding.newsapp_compose.presentation

import androidx.lifecycle.ViewModel
import com.hpcoding.newsapp_compose.domain.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
):ViewModel(){

}