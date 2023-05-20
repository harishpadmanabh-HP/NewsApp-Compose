package com.hpcoding.newsapp_compose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.hpcoding.newsapp_compose.R
import com.hpcoding.newsapp_compose.domain.NewsItem
import com.hpcoding.newsapp_compose.presentation.components.NetworkImage
import com.hpcoding.newsapp_compose.presentation.theme.Black
import com.hpcoding.newsapp_compose.presentation.theme.Cream
import com.hpcoding.newsapp_compose.utils.toLocalDate

@Composable
fun NewsDetails(
    newsItem: NewsItem?
) {
    newsItem?.let { item ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            NetworkImage(
                imageUrl = item.imageUrl,
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
                    .zIndex(1f)
            )
            Column(
                modifier = Modifier
                    .offset(x = 0.dp, y = (-34).dp)
                    .zIndex(4f)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp)
                    )
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))

            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = item.title, style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.monsterat_semibold)),
                        color = Black
                    ), modifier = Modifier.padding(10.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = item.description, style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.monsterat_regular)),
                        color = Black
                    ), modifier = Modifier.padding(10.dp)
                )

                if (!item.author.isNullOrEmpty())
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .background(color = Cream, shape = RoundedCornerShape(25))

                    ) {
                        Text(
                            text = "Author: ${item.author}", style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.monsterat_regular)),
                                color = Black
                            ), modifier = Modifier.padding(10.dp)
                        )
                    }
                Spacer(modifier = Modifier.height(12.dp))


                if (!item.publishedAt.isNullOrEmpty())
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .background(
                                color = Black.copy(.5f),
                                shape = RoundedCornerShape(25)
                            )

                    ) {
                        Text(
                            text = "Published At: ${item.publishedAt.toLocalDate()}",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.monsterat_regular)),
                                color = Cream
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                    }

            }
        }
    }


}