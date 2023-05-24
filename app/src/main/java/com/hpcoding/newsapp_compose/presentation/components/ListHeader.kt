package com.hpcoding.newsapp_compose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpcoding.newsapp_compose.R
import com.hpcoding.newsapp_compose.presentation.theme.Black
import com.hpcoding.newsapp_compose.presentation.theme.Olive

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListHeader(
    query: String,
    onSearchQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val keyboard = LocalSoftwareKeyboardController.current

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Discover", style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.mosterat_bold)),
                color = Black
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "News from all over the world.", style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.monsterat_regular)),
                color = Black.copy(alpha = .5f)
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        SearchBar(query = query, onValueChange = {
            onSearchQueryChange(it)
        }, onSubmitQuery = {
            onSearch(it)
            keyboard?.hide()
        })

        Spacer(modifier = Modifier.height(14.dp))


    }

}

@Composable
fun SearchBar(
    query: String,
    onValueChange: (String) -> Unit,
    onSubmitQuery: (String) -> Unit
) {
    var query by remember { //save state after scrolling
        mutableStateOf(TextFieldValue(query))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black.copy(.5f), shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {

        //Text field
        TextField(
            value = query,
            onValueChange = {
                query = it
                onValueChange(query.text)
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = Olive,
                textColor = Color.White
            ),
            placeholder = {
                Text(text = "Search")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions {
                if (query.text.isNotEmpty())
                    onSubmitQuery(query.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        )


        //search icon
        Image(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "search",
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    if (query.text.isNotEmpty())
                        onSubmitQuery(query.text)
                }
        )
    }

}

