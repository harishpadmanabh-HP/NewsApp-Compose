package com.hpcoding.newsapp_compose.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpcoding.newsapp_compose.R
import com.hpcoding.newsapp_compose.domain.NewsItem
import com.hpcoding.newsapp_compose.presentation.theme.Black
import com.hpcoding.newsapp_compose.presentation.theme.Cream

@Composable
fun NewsCard(
    item: NewsItem,
    onClick: (NewsItem) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(item)
            },
        shape = RoundedCornerShape(
            topEnd = 20.dp,
            bottomStart = 20.dp
        ),
        backgroundColor = Cream.copy(.2f),
        contentColor = Color.Black,
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            NetworkImage(imageUrl = item.imageUrl, modifier = Modifier.height(200.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.title, style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.monsterat_semibold)),
                    color = Black
                ), modifier = Modifier.padding(10.dp)
            )

        }
    }


}