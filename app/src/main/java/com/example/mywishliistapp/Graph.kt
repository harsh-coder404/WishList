package com.example.mywishliistapp

import android.content.Context
import androidx.room.Room
import com.example.mywishliistapp.data.WishDataBase
import com.example.mywishliistapp.data.WishRepository

// set up for dependency injection  // the graph will provide the dependencies to the rest of the app

object Graph {                           // object - used to declare an singleton (i.e only one instance will exist in the app)
    lateinit var database: WishDataBase    // non nullable

    val wishRepository by lazy {                   // lazy - it makes sure that the var is only initialized when it is actually needed rather loading everything at start
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDataBase::class.java, "wishlist.db").build()
        // builds the database once provide fun is called
    }

}

