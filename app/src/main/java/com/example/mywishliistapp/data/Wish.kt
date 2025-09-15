package com.example.mywishliistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// it uses SQL and stores data in tables
@Entity(tableName = "wish-table")                          // make those objects/data as an entity which we want to store permanently in device in room db
data class Wish(
    @PrimaryKey(autoGenerate = true)   // autogenerates the key(here id) so we dont have to do it manually
    val id: Long = 0L,                 // starts with 0 and then increments automatically
    @ColumnInfo(name = "wish-title")
    val title : String = "",
    @ColumnInfo(name = "wish-desc")     // naming the column
    val description:String = ""
)

object DummyWish{
    val wishList = listOf(
        Wish(title = "Chaser",
            description = "A Korean Thriller"),
        Wish(title = "Sopranos",
            description = "An HBO series"),
        Wish(title = "The Social Network",
            description = "Facebook early days"),
        Wish(title = "MoneyBall",
            description = "A Brad Pitt Movie "),
        Wish(title = "God Of War",
            description = "2018 story mode game revolving around Kratos"),
        Wish(title = "Red Dead Redemption 2",
            description = "A RockStar Game Production open world game ")
    )
}
