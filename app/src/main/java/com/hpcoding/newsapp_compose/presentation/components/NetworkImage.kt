package com.hpcoding.newsapp_compose.presentation.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hpcoding.newsapp_compose.R

@Composable
fun NetworkImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
        .size(100.dp)
        .zIndex(2f)
        .clip(shape = CircleShape)
        .padding(6.dp),
    showPlaceHolders: Boolean = true,
  //  placeHolder: Int = R.drawable.placeholder,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    contentDescription: String = "image",
    showTransition: Boolean = true,
    clearCache: Boolean = true

) {
    val context = LocalContext.current
    AsyncImage(
        model = if (showTransition) {
            ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build()
        } else imageUrl,
        contentDescription = contentDescription,
//        placeholder = if (showPlaceHolders) painterResource(id = placeHolder) else null,
//        error = if (showPlaceHolders) painterResource(id = placeHolder) else null,
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment
    )

}