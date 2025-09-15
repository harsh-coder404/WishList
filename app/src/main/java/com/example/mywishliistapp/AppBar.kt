package com.example.mywishliistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
){
    val navigationIcon : (@Composable () -> Unit)? =      // can define composable like this also
        if (!title.contains("WishList")){           // custom defining when to view back button (here if not contains 'wishlist' then show)
            {  // Braces for being used in composable
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,  // AutoMirrored - places the icon according to languages (left for english , right for urdu)
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }else{                   // if wishlist present then don't show
            null
        }
    TopAppBar(
        title = {
        Text(text = title,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp))
    },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),   // custom defined color in colors.xml file navigationIcon =
                                            // in color FF - no transparency and 00 - full transparency
        navigationIcon =navigationIcon
        )
}