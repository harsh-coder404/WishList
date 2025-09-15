package com.example.mywishliistapp

import android.app.Application

class WishListApp: Application() {        // Application is used to use any global context of the app
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)        // this wishList app is the context where we can do all the database work should setup
    }
}

// it must be registered in manifest to avoid crashes