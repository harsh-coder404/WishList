package com.example.mywishliistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(                          // Using Room DB
    entities = [Wish::class],
    version =1,             // it is literally the version of table (suppose we make changes in Column in future we will set the version to 2)
    exportSchema = false
    )
abstract class WishDataBase: RoomDatabase() {
    abstract fun wishDao(): WishDao
}