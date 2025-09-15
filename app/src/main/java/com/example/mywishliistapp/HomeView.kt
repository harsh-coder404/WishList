package com.example.mywishliistapp

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywishliistapp.data.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {AppBarView(title = "WishList")},

        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 28.dp),    // all - padding in all direction
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {
                    Toast.makeText(context,"FAB Button Clicked", Toast.LENGTH_LONG).show()

                    navController.navigate(Screen.AddScreen.route + "/0L")
                }
            )
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    ) {
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())    // collect all wishes and put it in wishList
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
                items(wishList.value, key={wish -> wish.id}){    // key removes the redundant space that is left when an item is dismissed
                    wish ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                                viewModel.deleteWish(wish)
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                                     val color by animateColorAsState(
                                         if (dismissState.dismissDirection
                                             == DismissDirection.EndToStart) Color.Red else Color.Transparent
                                         , label = ""
                                     )
                            val alignment = Alignment.CenterEnd
                            Box(
                                Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),   // both horizontal sides
                                contentAlignment = alignment
                            ){
                                Icon(Icons.Default.Delete,
                                    contentDescription = "Delete Icon",
                                    tint = Color.White
                                )
                            }
                        },
                        directions = setOf( DismissDirection.EndToStart),
                        dismissThresholds = {FractionalThreshold(0.5f)},                // how much drag required to dismiss
                        dismissContent = {           // the content that we want to dismiss
                            WishItem(wish = wish) {
                                val id = wish.id
                                navController.navigate(Screen.AddScreen.route + "/$id")
                            }
                        }
                    )
                }
            }
        }
    }

@Composable
fun WishItem(wish: Wish, onClick: () -> Unit){    // how each wish item should look like

    Card(modifier = Modifier
        .fillMaxSize()              // Card - use to design
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        },
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)  // makes text bold
            Text(text = wish.description)
        }
    }
}