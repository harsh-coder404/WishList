package com.example.mywishliistapp

sealed class Screen(val route:String){   // sealed - can't be inherited
    object HomeScreen:  Screen("home_screen")
    object AddScreen: Screen("add_screen")
}